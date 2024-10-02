export type LoginValidationResponse = {
    result: boolean,
    errorOrigin: ("email" | "password")[]
}