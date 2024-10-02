import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { UserDataSliceInitialState } from "../../types/redux/userDataSliceInitialState";
import { LoginRequest } from "../../types/auth/loginRequest";
import { RegisterRequest } from "../../types/auth/registerRequest";
import { UserRoles } from "../../enums/userRoles";
import { loginDataValidation } from "../../helpers/authDataValidation";
import axios from "axios";
import { UpdateTokensRequest } from "../../types/auth/updateTokensRequest";

const initialState: UserDataSliceInitialState = {
    loginState: false,
    token: null,
    userDetails: {
        firstName: "",
        surname: "",
        email: "",
        password: "",
        role: "UNAUTHENTICATED"
    },
    loginLoadingState: "IDLE",
}

export const login = createAsyncThunk('/login', async (payload: LoginRequest, { rejectWithValue }) => {
    try {
        const response = await axios.post("http://localhost:8080/auth/login",
            payload,
            {
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        )
        if (response.status == 200) {
            return response.data
        }
        else return rejectWithValue(response.status)
    }
    catch (e) {
        console.log(e)
        return rejectWithValue("Invalid credentials")
    }
})

export const register = createAsyncThunk('/register', async (payload: RegisterRequest, { rejectWithValue }) => {
    if (loginDataValidation(payload).result) {
        try {
            const response = await axios.post("http://localhost:8080/auth/register",
                payload,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            )
            if (response.status == 200) {
                return response.data
            }
            else return rejectWithValue(response.status)
        }
        catch (e) {
            console.log(e)
            return rejectWithValue("Invalid credentials")
        }
    }
    else throw new Error("Provided data is invalid, check your credentials and try again")
})


export const userDataSlice = createSlice({
    name: "userData",
    initialState,
    reducers: {
        logout(state: UserDataSliceInitialState) {
            state.token = "",
                state.loginState = false,
                state.userDetails = {
                    role: "UNAUTHENTICATED",
                    firstName: "",
                    surname: "",
                    password: "",
                    email: ""
                }
        },
        updateTokens(state: UserDataSliceInitialState, { payload }: { payload: UpdateTokensRequest }) {
            state.token = payload.token
        }
    },
    extraReducers(builder) {
        builder
            .addCase(login.pending, (state: UserDataSliceInitialState) => {
                state.loginLoadingState = "LOADING"
            })
            .addCase(login.fulfilled, (state: UserDataSliceInitialState, action) => {
                state.loginLoadingState = "FULFILLED"
                state.userDetails = {
                    role: action.payload?.role as keyof typeof UserRoles,
                    firstName: action.payload?.firstName as String,
                    surname: action.payload?.surname as String,
                    password: action.payload?.password as String,
                    email: action.payload?.email as String
                }
                state.token = action.payload?.token
                state.loginState = true
                state.loginLoadingState = "IDLE"
            })
            .addCase(login.rejected, (state: UserDataSliceInitialState, errorMessage) => {
                console.log(errorMessage)
                userDataSlice.caseReducers.logout(state)
            }),
            builder
                .addCase(register.pending, (state: UserDataSliceInitialState) => {
                    state.loginLoadingState = "LOADING"
                })
                .addCase(register.fulfilled, (state: UserDataSliceInitialState) => {
                    state.loginLoadingState = "FULFILLED"
                    userDataSlice.caseReducers.logout(state)
                })
                .addCase(register.rejected, (state: UserDataSliceInitialState, errorMessage) => {
                    console.log(errorMessage)
                    state.loginLoadingState = "REJECTED"
                })
    }
})


export default userDataSlice.reducer
export const { logout, updateTokens } = userDataSlice.actions