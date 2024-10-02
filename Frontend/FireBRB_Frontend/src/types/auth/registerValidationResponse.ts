export type RegisterValidationResponse = {
    result: boolean,
    errorOrigin: ("email" | "password" | "confirmPassword")[]
}