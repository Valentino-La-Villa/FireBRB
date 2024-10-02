
declare module 'redux-persist/lib/storage' {
    const storage: {
        setItem: (key: string, value: string) => Promise<void>;
        getItem: (key: string) => Promise<string | null>;
        removeItem: (key: string) => Promise<void>;
    };
    export default storage;
}

declare module 'redux-persist/es/persistStore' {
    import { Persistor } from "redux-persist";
    const persistStore: (store: any, config?: any) => Persistor;
    export default persistStore
}