import { Offcanvas } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { NavLink } from 'react-router-dom';
import { useAppSelector } from '../../../redux/store';

const Navigation = () => {

    const burgerMenuBreakpoint = "lg"
    const userData = useAppSelector(state => state.userData.userDetails)

    return (
        <>
            <Navbar bg='dark' expand={burgerMenuBreakpoint} data-bs-theme="dark" key={burgerMenuBreakpoint}>
                <Container fluid>
                    <Navbar.Brand className='d-none d-sm-block'>
                        <NavLink to="/">
                            <h2 className='fs-3'>Welcome to FireBRB</h2>
                        </NavLink>
                    </Navbar.Brand>

                    <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${burgerMenuBreakpoint}`} />
                    <Navbar.Offcanvas
                        id={`offcanvasNavbar-expand-${burgerMenuBreakpoint}`}
                        aria-labelledby={`offcanvasNavbarLabel-expand-${burgerMenuBreakpoint}`}
                        placement="end"
                        className="bg-dark"
                        data-bs-theme="dark"
                    >
                        <Offcanvas.Header closeButton className='mt-3 me-3'>
                            <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${burgerMenuBreakpoint}`}>
                                {userData.role == "ADMIN" || userData.role == "USERREGULAR"
                                    ? <p className='hello-message fs-5'>Hello, {userData.firstName}</p>
                                    : <></>}
                            </Offcanvas.Title>
                        </Offcanvas.Header>
                        <Offcanvas.Body>
                            <Nav className="justify-content-end flex-grow-1 pe-3">
                                <Navbar.Collapse id="basic-navbar-nav">
                                    <Nav className="me-auto"></Nav>
                                </Navbar.Collapse>

                                {userData.role == "USERREGULAR" // Navbar display for regular users
                                    ?
                                    <Nav.Link>
                                        <NavLink to="/myProfile">
                                            <h2 className='d-flex justify-content-end text-grey'>My Profile</h2>
                                        </NavLink>
                                    </Nav.Link>


                                    : userData.role == "ADMIN" // Navbar display for admins
                                        ?
                                        <>
                                            <Nav.Link>
                                                <NavLink to="/admin">
                                                    <h3 className='d-flex justify-content-end text-grey'>Admin</h3>
                                                </NavLink>
                                            </Nav.Link>

                                            <Nav.Link>
                                                <NavLink to="/myProfile">
                                                    <h3 className='d-flex justify-content-end text-grey'>My profile</h3>
                                                </NavLink>
                                            </Nav.Link>
                                        </>

                                        :
                                        <>
                                            <Nav.Link>
                                                <NavLink to="/login">
                                                    <h3 className='d-flex justify-content-end text-grey'>Login</h3>
                                                </NavLink>
                                            </Nav.Link>

                                            <Nav.Link>
                                                <NavLink to="/register">
                                                    <h3 className='d-flex justify-content-end text-grey'>Register</h3>
                                                </NavLink>
                                            </Nav.Link>
                                        </>}

                                <h3 className='text-grey'>
                                    <NavDropdown drop="down" align="end" title="Booking">
                                        <NavDropdown.Item>
                                            <NavLink to="/rentables">
                                                <p>Browse all</p>
                                            </NavLink>
                                        </NavDropdown.Item>

                                        <NavDropdown.Divider />

                                        <NavDropdown.Item>
                                            <NavLink to="/rentables">
                                                <p>Appartments</p>
                                            </NavLink>
                                        </NavDropdown.Item>

                                        <NavDropdown.Item>
                                            <NavLink to="/rentables">
                                                <p>Cabins</p>
                                            </NavLink>
                                        </NavDropdown.Item>

                                        <NavDropdown.Item>
                                            <NavLink to="rentables">
                                                <p>Hotels</p>
                                            </NavLink>
                                        </NavDropdown.Item>

                                        <NavDropdown.Item>
                                            <NavLink to="rentables">
                                                <p>Houses</p>
                                            </NavLink>
                                        </NavDropdown.Item>

                                        <NavDropdown.Item>
                                            <NavLink to="rentables">
                                                <p>Hostels</p>
                                            </NavLink>
                                        </NavDropdown.Item>
                                    </NavDropdown>
                                </h3>
                            </Nav>
                        </Offcanvas.Body>
                    </Navbar.Offcanvas>
                </Container>
            </Navbar>

        </>
    )
}

export default Navigation