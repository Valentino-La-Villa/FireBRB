import { NavLink, useNavigate } from "react-router-dom"
import logo from "../../assets/brand/Logo.png"
import Navigation from "./nav/Navigation"

export default function Header() {

    const navigate = useNavigate()

    return (
        <header>
            <div className="bg-dark text-white position-fixed container-fluid z-3">
                <div className="d-flex justify-content-between align-items-center">
                    <section className="col-3 col-2-sm d-flex align-items-center p-2">
                        <img src={logo} alt="" className="img-fluid" style={{ cursor: "pointer" }} onClick={() => { navigate("/") }} // Doing this with the useNavigate hook because the img sizing will break if you just wrap the <img/> in the <NavLink> element
                        />
                        <NavLink to="/">
                            <h1 className="ms-2 fw-bold fs-4">FireBRB</h1>
                        </NavLink>
                    </section>

                    <nav className="col-8 col-9-sm d-flex justify-content-end">
                        <Navigation />
                    </nav>
                </div>
            </div>
        </header >
    )
}

