import axios from "axios";
import { useAppDispatch, useAppSelector } from "../../redux/store";
import { logout, updateTokens } from "../../redux/slices/userDataSlice";

const useAxiosInterceptor = () => {
    const state = useAppSelector(state => state.userData)
    const reduxStoredToken = state.token
    const dispatch = useAppDispatch()

    axios.interceptors.request.use(
        (config) => {
            if (reduxStoredToken) {
                config.headers.Authorization = `Bearer ${reduxStoredToken}`; // Attaches token to every request
            }
            return config
        }, (error) => {
            return Promise.reject(error)
        })

    axios.interceptors.response.use(
        (response) => {
            return response
        },
        async (error) => {
            const originalRequest = error.config
            try {
                if (error?.response.status == 401 && !originalRequest._retry) {
                    originalRequest._retry = true
                    const response = await axios.get("http://localhost:8080/auth/refreshToken")

                    const newAccessToken = response.data.token

                    dispatch(updateTokens({ token: newAccessToken }))

                    originalRequest.headers.Authorization = `Bearer ${newAccessToken}`

                    return axios(originalRequest)
                }
            } catch (refreshError) {
                console.error("Error refreshing token: ", refreshError)
                dispatch(logout())
                return Promise.reject(refreshError)
            }
        }
    )
}

export default useAxiosInterceptor