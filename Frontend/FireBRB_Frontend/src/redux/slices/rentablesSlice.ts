import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { RentablesSliceInitialState } from "../../types/redux/rentablesSliceInitialState";
import axios from "axios";
import { Rentable } from "../../types/entities/rentable";
import { RentableType } from "../../types/entities/rentableType";

const initialState: RentablesSliceInitialState = {
    localStoredRentables: {
        data: [],
        status: 'IDLE',
        error: null,
        dateOfSnapshot: null
    },
    currentlyAccessedObject: {
        data: null,
        status: 'IDLE',
        error: null,
        dateOfSnapshot: null
    },
    localStoredRentableTypes: {
        data: [],
        status: 'IDLE',
        error: null,
        dateOfSnapshot: null
    }
}

export const findAllFromAPI = createAsyncThunk('rentables/findAllFromAPI', async () => {
    const data: Rentable[] = await axios.get("http://localhost:8080/rentables").then(
        response => response.data
    ).catch(err => {
        console.log(err)
    })
    return data
})

export const findByIdFromAPI = createAsyncThunk('rentables/findByIdFromAPI', async (id: Number) => {
    const data: Rentable = await axios.get(`http://localhost:8080/rentables/${id}`).then(
        response => response.data
    ).catch(err => console.log(err))
    return data
})

export const findAllRentableTypesFromAPI = createAsyncThunk('rentables/findAllRentableTypes', async () => {
    const data: RentableType[] = await axios.get('http://localhost:8080/rentableTypes').then(
        response => response.data
    ).catch(err => console.log(err))
    return data
})

export const forceUpdateDatabase = createAsyncThunk('rentables/forceUpdateDatabase', async (_, { dispatch }) => {
    try {
        await dispatch(findAllFromAPI())
        await dispatch(findAllRentableTypesFromAPI())
    } catch (err) { console.log(err) }
})

// export const updateNecessaryFieldsFromDatabase = createAsyncThunk('rentables/updateNecessaryFieldsFromDatabase', async (_, { dispatch, getState }) => { // For future implementation - this should check if date.now() is 15 minutes apart from the lastUpdatedDate. I'll do this later since I want to avoid innecesary crashes early-on
// })


export const rentablesSlice = createSlice({
    name: "rentables",
    initialState,
    reducers: {

    },
    extraReducers(builder) {
        builder
            .addCase(findAllFromAPI.pending, (state: RentablesSliceInitialState) => {
                state.localStoredRentables.status = 'LOADING'
            })
            .addCase(findAllFromAPI.fulfilled, (state: RentablesSliceInitialState, action) => {
                state.localStoredRentables.data = action.payload
                state.localStoredRentables.status = 'FULFILLED'
                state.localStoredRentables.dateOfSnapshot = new Date()
            })
            .addCase(findAllFromAPI.rejected, (state: RentablesSliceInitialState) => {
                state.localStoredRentables.status = 'REJECTED'
            })

        builder
            .addCase(findByIdFromAPI.pending, (state: RentablesSliceInitialState) => {
                state.currentlyAccessedObject.status = 'LOADING'
            })
            .addCase(findByIdFromAPI.fulfilled, (state: RentablesSliceInitialState, action) => {
                state.currentlyAccessedObject.data = action.payload
                state.currentlyAccessedObject.status = 'FULFILLED'
                state.currentlyAccessedObject.dateOfSnapshot = new Date()
            })
            .addCase(findByIdFromAPI.rejected, (state: RentablesSliceInitialState) => {
                state.currentlyAccessedObject.status = 'REJECTED'
            })

        builder
            .addCase(findAllRentableTypesFromAPI.pending, (state: RentablesSliceInitialState) => {
                state.localStoredRentableTypes.status = 'LOADING'
            })
            .addCase(findAllRentableTypesFromAPI.fulfilled, (state: RentablesSliceInitialState, action) => {
                state.localStoredRentableTypes.data = action.payload
                state.localStoredRentableTypes.status = 'FULFILLED'
                state.localStoredRentableTypes.dateOfSnapshot = new Date()
            })
            .addCase(findAllRentableTypesFromAPI.rejected, (state: RentablesSliceInitialState) => {
                state.localStoredRentableTypes.status = 'REJECTED'
            })
    }
})

export default rentablesSlice.reducer
export const { } = rentablesSlice.actions