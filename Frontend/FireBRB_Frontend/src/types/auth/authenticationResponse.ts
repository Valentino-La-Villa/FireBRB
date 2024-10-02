import { UserRoles } from "../enums/userRoles"

export type AuthenticationResponse = {
    token: String,
    role: keyof typeof UserRoles,
    firstName: String,
    surname: String,
    email: String
}