import { LoadingStates } from "../../enums/loadingStates"
import { Rentable } from "../entities/rentable"
import { RentableType } from "../entities/rentableType"

export type RentablesSliceInitialState = {
    localStoredRentables: {
        data: Rentable[],
        status: keyof typeof LoadingStates,
        error: String | null,
        dateOfSnapshot: Date | null
    },
    currentlyAccessedObject: {
        data: Rentable | null,
        status: keyof typeof LoadingStates,
        error: String | null,
        dateOfSnapshot: Date | null
    },
    localStoredRentableTypes: {
        data: RentableType[],
        status: keyof typeof LoadingStates,
        error: String | null,
        dateOfSnapshot: Date | null
    }
}
