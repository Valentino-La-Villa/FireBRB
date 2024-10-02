import { createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios"
import { RentableTypeRequest } from "../../types/admin/RentableTypeRequest"
import { RentableRequest } from "../../types/admin/RentableRequest"


const initialState = {
    loadingState: "IDLE"
}

export const postRentableType = createAsyncThunk('/post/rentableType', async (payload: RentableTypeRequest, { rejectWithValue }) => {
    try {
        const response = await axios.post("http://localhost:8080/rentableTypes",
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

export const postRentable = createAsyncThunk('post/rentable', async (payload: RentableRequest, { rejectWithValue }) => {
    try {
        const response = await axios.post("http://localhost:8080/rentables",
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

export const adminSlice = createSlice({
    name: "admin",
    initialState,
    reducers: {

    },
    extraReducers(builder) {
        builder
            .addCase(postRentableType.pending, (state) => {
                state.loadingState = "LOADING"
            })
            .addCase(postRentableType.fulfilled, (state) => {
                state.loadingState = "FULFILLED"
            })
            .addCase(postRentableType.rejected, (state) => {
                state.loadingState = "REJECTED"
            })


            .addCase(postRentable.pending, (state) => {
                state.loadingState = "LOADING"
            })
            .addCase(postRentable.fulfilled, (state) => {
                state.loadingState = "FULFILLED"
            })
            .addCase(postRentable.rejected, (state) => {
                state.loadingState = "REJECTED"
            })
    }
})

export default adminSlice.reducer