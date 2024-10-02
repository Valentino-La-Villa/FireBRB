import { LoadingStates } from "../../enums/loadingStates"
import { UserRoles } from "../../enums/userRoles"

export type UserDataSliceInitialState = {
    token: String | null,
    loginState: boolean,
    userDetails: {
        firstName: String,
        surname: String,
        email: String,
        password: String,
        role: keyof typeof UserRoles
    },
    loginLoadingState: keyof typeof LoadingStates
}