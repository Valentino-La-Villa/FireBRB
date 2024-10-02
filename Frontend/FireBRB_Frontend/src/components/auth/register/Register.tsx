import { register } from "../../../redux/slices/userDataSlice"
import { Form } from "react-bootstrap"
import { useAppDispatch } from "../../../redux/store"
import { useState } from "react"
import { NavLink } from "react-router-dom"
import { registerDataValidation } from "../../../helpers/authDataValidation"
import { Tooltip } from "react-tooltip"
import { RegisterRequest } from "../../../types/auth/registerRequest"
import { RegisterValidationResponse } from "../../../types/auth/registerValidationResponse"

export default function Register() {

    const dispatch = useAppDispatch()

    const [formData, setFormData] = useState<RegisterRequest>({
        email: "",
        password: "",
        firstName: "",
        surname: "",
        confirmPassword: "",
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
        dispatch(register(formData))
    }

    const registerValidation: RegisterValidationResponse = registerDataValidation(formData)

    return <main className="container-fluid d-flex justify-content-center">
        <div className="col-sm-10 col-md-8 col-lg-7 col-xl-6 col-xxl-5 mt-5">
            <p className="mb-3">Already have an account? <NavLink className="register-login-link text-success" to="/login">Log in</NavLink></p>
            <Form>
                <div className="d-flex flex-column gap-4 bg-primary p-4 pb-5 rounded-3 text-white">
                    <Form.Group className="d-flex flex-column align-items-start">
                        <Form.Label htmlFor="registerFirstNameInput">
                            <p className="text-left">First name</p>
                        </Form.Label>
                        <Form.Control
                            type="text"
                            name="firstName"
                            id="registerFirstNameInput"
                            placeholder="John"
                            onChange={handleForm} />
                    </Form.Group>

                    <Form.Group className="d-flex flex-column align-items-start">
                        <Form.Label htmlFor="registerSurnameInput">
                            <p className="text-left">Surname</p>
                        </Form.Label>
                        <Form.Control
                            type="text"
                            name="surname"
                            id="registerSurnameInput"
                            placeholder="Doe"
                            onChange={handleForm} />
                    </Form.Group>
                    <Form.Group className="d-flex flex-column align-items-start">
                        <Form.Label htmlFor="registerEmailInput">
                            <p className="text-left">Email address</p>
                        </Form.Label>
                        <Form.Control
                            type="email"
                            name="email"
                            id="registerEmailInput"
                            placeholder="name@service.com"
                            onChange={handleForm} />
                    </Form.Group>

                    <Form.Group className="d-flex flex-column align-items-start">
                        <Form.Label htmlFor="registerPasswordInput">
                            <p>Password</p>
                        </Form.Label>
                        <Form.Control
                            type="password"
                            id="registerPasswordInput"
                            name="password"
                            aria-describedby="passwordHelpBlock"
                            onChange={handleForm}
                        />
                        <Form.Text id="passwordHelpBlock" muted>
                            <p className="text-white text-left" style={{ pointerEvents: "none" }}>
                                Your password must be 8-20 characters long.
                            </p>
                        </Form.Text>
                    </Form.Group>

                    <Form.Group className="d-flex flex-column align-items-start">
                        <Form.Label htmlFor="registerConfirmPasswordInput">
                            <p>Confirm password</p>
                        </Form.Label>
                        <Form.Control
                            type="password"
                            id="registerConfirmPasswordInput"
                            name="confirmPassword"
                            aria-describedby="passwordHelpBlock"
                            onChange={handleForm}
                        />
                    </Form.Group>

                </div>
                <div className="d-flex flex-column align-items-end mt-5">
                    <div
                        data-tooltip-id="register-button-disabled"
                        data-tooltip-place="top">
                        <button disabled={!registerValidation.result} onClick={handleSubmit} className="btn btn-success text-white">
                            <p>Create my account</p>
                        </button>
                    </div>
                </div>

            </Form>

            {registerValidation.result
                ? <></>
                : <Tooltip id="register-button-disabled">
                    <div className="d-flex align-items-start flex-column">

                        {registerValidation.errorOrigin.includes("email")
                            ?
                            <p>• The email address you entered is invalid</p>
                            : <></>}

                        {registerValidation.errorOrigin.includes("password")
                            ?
                            <p>• Your password must be at least 8 characters long</p>
                            : <></>}

                        {registerValidation.errorOrigin.includes("confirmPassword")
                            ?
                            <p>• 'Password' and 'Confirm Password' fields are different</p>
                            : <></>}
                    </div>
                </Tooltip>}

        </div>
    </main>
}