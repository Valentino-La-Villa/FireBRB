import { Navigate } from "react-router"
import { ReactElement } from 'react'
import { useAppSelector } from "../../redux/store"

export const useAuthRouterValidations = () => {

    const userState = useAppSelector(state => state.userData)

    const LoginValidation = ({ children }: { children: ReactElement }): ReactElement => {
        if (userState.userDetails.role == "ADMIN" || userState.userDetails.role == "USERREGULAR") {
            return children
        } else return <Navigate to={"/login"} />
    }

    const AdminValidation = ({ children }: { children: ReactElement }): ReactElement => {
        if (userState.userDetails.role == "ADMIN") {
            return children
        }
        else return <Navigate to={"/"} />
    }

    const NotLoggedInValidation = ({ children }: { children: ReactElement }) => {
        if (userState.loginState == false) {
            return children
        } else
            return <Navigate to={"/"} />
    }

    return {
        LoginValidation: LoginValidation,
        AdminValidation: AdminValidation,
        NotLoggedInValidation: NotLoggedInValidation,
    }

}