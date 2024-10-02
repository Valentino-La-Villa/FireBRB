import { configureStore } from "@reduxjs/toolkit";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import userDataSlice from "./slices/userDataSlice";
import storage from "redux-persist/lib/storage";
import persistStore from "redux-persist/es/persistStore";
import persistReducer from "redux-persist/es/persistReducer";
import { adminSlice } from "./slices/adminSlice";
import { rentablesSlice } from "./slices/rentablesSlice";

const persistConfig = {
    key: 'userData',
    storage
}

const persistedReducer = persistReducer(persistConfig, userDataSlice)

export const store = configureStore({
    reducer: {
        userData: persistedReducer,
        admin: adminSlice.reducer,
        rentables: rentablesSlice.reducer
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        serializableCheck: false,
    })
})

export const useAppDispatch: () => typeof store.dispatch = useDispatch
export const useAppSelector: TypedUseSelectorHook<ReturnType<typeof store.getState>> = useSelector
export const persistor = persistStore(store)