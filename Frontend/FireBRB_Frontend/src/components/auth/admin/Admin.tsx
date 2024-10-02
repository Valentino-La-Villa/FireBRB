
import { Accordion } from "react-bootstrap"
import AddRentableType from "./rentableTypeOperations/AddRentableType"
import AddRentable from "./rentableOperations/AddRentable"


const Admin = () => {

    return (
        <main className="my-5">
            <h1 className="mb-4">Admin dashboard</h1>

            <Accordion alwaysOpen>

                <h2 className="mb-4">Rentable types:</h2>

                <AddRentableType />

                <h2 className="my-4">Rentables:</h2>

                <AddRentable />

            </Accordion>
        </main>
    )
}

export default Admin