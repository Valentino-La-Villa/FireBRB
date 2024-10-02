import { Accordion, Form } from 'react-bootstrap'
import { useAppDispatch } from '../../../../redux/store'
import { postRentableType } from '../../../../redux/slices/adminSlice'
import { useRef } from 'react'

const AddRentableType = () => {
    const addRentableTypeNameRef = useRef(null)
    const dispatch = useAppDispatch()


    const POSTRentableType = (e: any) => {
        e.preventDefault()
        const name = addRentableTypeNameRef.current
        const value: String = (name as any).value // this is the only way typescript will let me through, I think you must cast type 'any' here because addRentableTypeNameRef starts as null
        if (value != null && value != "") {
            dispatch(postRentableType({ name: value }))
        } else console.log("Could not fulfill request, provided data is invalid")
    }

    return (
        <Accordion.Item eventKey="0">
            <Accordion.Header>Add Rentable Type</Accordion.Header>
            <Accordion.Body>
                <Form>
                    <Form>
                        <Form.Group className="d-flex flex-column align-items-start">
                            <Form.Label htmlFor="addRentableTypeName">
                                <p className="text-left">Type name (e.g. 'Hostel')</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableTypeName"
                                id="addRentableTypeName"
                                ref={addRentableTypeNameRef} />
                        </Form.Group>
                    </Form>
                    <div className="d-flex justify-content-end w-100 mt-3">
                        <button className="btn btn-primary"
                            onClick={POSTRentableType}>Submit</button>
                    </div>
                </Form>
            </Accordion.Body>
        </Accordion.Item>
    )
}

export default AddRentableType