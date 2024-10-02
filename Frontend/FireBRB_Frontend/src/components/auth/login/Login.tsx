import { Form } from "react-bootstrap"
import { login } from "../../../redux/slices/userDataSlice"
import { useAppDispatch, useAppSelector } from "../../../redux/store"
import { useState } from "react"
import { LoginRequest } from "../../../types/auth/loginRequest"
import { NavLink } from "react-router-dom"
import { loginDataValidation } from "../../../helpers/authDataValidation"
import { LoginValidationResponse } from "../../../types/auth/loginValidationResponse"
import { Tooltip } from "react-tooltip"

export default function Login() {

    const dispatch = useAppDispatch()

    const [formData, setFormData] = useState<LoginRequest>({
        email: "",
        password: ""
    })

    const handleForm = (event: any) => {
        setFormData(prev => {
            return {
                ...prev,
                [event.target.name]: event.target.value
            }
        })
    }

    const handleSubmit = (event: any) => {
        event.preventDefault()
        dispatch(login(formData))
    }

    const loginValidation: LoginValidationResponse = loginDataValidation(formData)

    return (
        <main className="container-fluid d-flex justify-content-center">
            <div className="col-sm-10 col-md-8 col-lg-7 col-xl-6 col-xxl-5 mt-5">
                <p className="mb-3">Not a member yet? <NavLink className="register-login-link text-success" to="/register">Create an account</NavLink> to get started!</p>
                <Form>
                    <div className="d-flex flex-column gap-4 bg-primary p-4 pb-5 rounded-3 text-dark">
                        <Form.Group className="d-flex flex-column align-items-start">
                            <Form.Label htmlFor="loginEmailInput">
                                <p className="text-left">Email address</p>
                            </Form.Label>
                            <Form.Control
                                type="email"
                                name="email"
                                id="loginEmailInput"
                                placeholder="name@service.com"
                                onChange={handleForm} />
                        </Form.Group>

                        <Form.Group className="d-flex flex-column align-items-start">
                            <Form.Label htmlFor="loginPasswordInput">
                                <p>Password</p>
                            </Form.Label>
                            <Form.Control
                                type="password"
                                id="loginPasswordInput"
                                name="password"
                                aria-describedby="passwordHelpBlock"
                                onChange={handleForm}
                            />
                        </Form.Group>
                    </div>


                    <div className="d-flex flex-column align-items-end mt-5">
                        <div
                            data-tooltip-id="login-button-disabled"
                            data-tooltip-place="top">
                            <button disabled={!loginValidation.result} onClick={handleSubmit} className="btn btn-success text-white">
                                <p>Log in</p>
                            </button>
                        </div>
                    </div>
                </Form>


                {loginValidation.result
                    ? <></>
                    : <Tooltip id="login-button-disabled">
                        <div className="d-flex align-items-start flex-column">

                            {loginValidation.errorOrigin.includes("email")
                                ?
                                <p>• The email address you entered is invalid</p>
                                : <></>}

                            {loginValidation.errorOrigin.includes("password")
                                ?
                                <p>• All passwords are at least 8 characters long</p>
                                : <></>}
                        </div>
                    </Tooltip>}
            </div>
        </main >
    )
}