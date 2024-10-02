import { LoginRequest } from "../types/auth/loginRequest";
import { LoginValidationResponse } from "../types/auth/loginValidationResponse";
import { RegisterRequest } from "../types/auth/registerRequest";
import { RegisterValidationResponse } from "../types/auth/registerValidationResponse";

const validateEmail = (email: String) => /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email as string)

const validatePassword = (password: String) => password.length >= 8

const validateConfirmPassword = (password: String, confirmPassword: String) => password === confirmPassword

export function loginDataValidation(loginData: LoginRequest): LoginValidationResponse {
    const errorOrigin: ("email" | "password")[] = []

    const passwordState: boolean = validatePassword(loginData.password)
    const emailState: boolean = validateEmail(loginData.email)

    if (emailState && passwordState) {
        return {
            result: true,
            errorOrigin: []
        }
    }
    else if (!emailState) {
        errorOrigin.push("email")
    }
    if (!passwordState) {
        errorOrigin.push("password")
    }
    return {
        result: false,
        errorOrigin: errorOrigin
    }
}

export function registerDataValidation(registrationData: RegisterRequest): RegisterValidationResponse {
    const errorOrigin: ("email" | "password" | "confirmPassword")[] = []

    const passwordState: boolean = validatePassword(registrationData.password)
    const emailState: boolean = validateEmail(registrationData.email)
    const confirmPasswordState: boolean = validateConfirmPassword(registrationData.password, registrationData.confirmPassword)

    if (
        passwordState &&
        emailState &&
        confirmPasswordState) {
        return {
            result: true,
            errorOrigin: []
        }
    }
    else if (!emailState) {
        errorOrigin.push("email")
    }
    if (!passwordState) {
        errorOrigin.push("password")
    }
    if (!confirmPasswordState) {
        errorOrigin.push("confirmPassword")
    }
    return {
        result: false,
        errorOrigin: errorOrigin
    }
}