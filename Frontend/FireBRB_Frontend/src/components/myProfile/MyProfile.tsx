import { logout } from "../../redux/slices/userDataSlice"
import { useAppDispatch } from "../../redux/store"

export default function MyProfile() {

    const dispatch = useAppDispatch()

    return <main>
        <p>My profile</p>
        <button onClick={() => dispatch(logout())} className="btn-primary btn">Log out</button>
    </main>
}