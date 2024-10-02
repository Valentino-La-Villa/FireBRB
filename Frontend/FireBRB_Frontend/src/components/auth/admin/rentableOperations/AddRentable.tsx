import { Accordion, Form } from 'react-bootstrap'
import { useAppDispatch } from '../../../../redux/store'
import { useRef } from 'react'
import { postRentable } from '../../../../redux/slices/adminSlice'
import { RentableRequest } from '../../../../types/admin/RentableRequest'


const AddRentable = () => {

    const dispatch = useAppDispatch()

    const addRentableName = useRef(null)
    const addRentableAddress = useRef(null)
    const addRentableCity = useRef(null)
    const addRentableRegion = useRef(null)
    const addRentableCountry = useRef(null)
    const addRentablePricePerNightUSD = useRef(null)
    const addRentableRentableTypeId = useRef(null)
    const addRentableAssociatedImgsUrl = useRef(null)
    const addRentableStarRating = useRef(null)
    const addRentableMapsURL = useRef(null)


    const POSTRentable = (e: any) => {
        e.preventDefault()
        const form: RentableRequest = {
            name: (addRentableName.current as any).value,
            address: (addRentableAddress.current as any).value,
            city: (addRentableCity.current as any).value,
            region: (addRentableRegion.current as any).value,
            country: (addRentableCountry.current as any).value,
            pricePerNightUSD: Number((addRentablePricePerNightUSD.current as any).value),
            rentableTypeId: Number((addRentableRentableTypeId.current as any).value),
            associatedImgsURL: [(addRentableAssociatedImgsUrl.current as any).value],
            starRating: Number((addRentableStarRating.current as any).value)
        }
        console.log(form)

        if (form.starRating <= 5) {
            dispatch(postRentable(form))
        } else console.log("Could not fulfill request, provided data is invalid")
    }

    return (
        <Accordion.Item eventKey="1" className='z-index-1'>
            <Accordion.Header>Add Rentable</Accordion.Header>
            <Accordion.Body>
                <Form>
                    <Form>
                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableName">
                                <p className="text-left">Name</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableName"
                                id="addRentableName"
                                ref={addRentableName} />
                        </Form.Group>


                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableAddress">
                                <p className="text-left">Address</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableAddress"
                                id="addRentableAddress"
                                ref={addRentableAddress} />
                        </Form.Group>


                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableCity">
                                <p className="text-left">City</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableCity"
                                id="addRentableCity"
                                ref={addRentableCity} />
                        </Form.Group>




                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableRegion">
                                <p className="text-left">Region</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableRegion"
                                id="addRentableRegion"
                                ref={addRentableRegion} />
                        </Form.Group>




                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableCountry">
                                <p className="text-left">Country</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableCountry"
                                id="addRentableCountry"
                                ref={addRentableCountry} />
                        </Form.Group>




                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentablePricePerNightUSD">
                                <p className="text-left">Price per night</p>
                            </Form.Label>
                            <Form.Control
                                type="number"
                                name="addRentablePricePerNightUSD"
                                id="addRentablePricePerNightUSD"
                                ref={addRentablePricePerNightUSD}
                                step="0.1" />
                        </Form.Group>




                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableRentableTypeId">
                                <p className="text-left">Rentable Type ID (options list pending)</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableRentableTypeId"
                                id="addRentableRentableTypeId"
                                ref={addRentableRentableTypeId} />
                        </Form.Group>


                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableAssociatedImgsUrl">
                                <p className="text-left">Associated image (URL)</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableAssociatedImgsUrl"
                                id="addRentableAssociatedImgsUrl"
                                ref={addRentableAssociatedImgsUrl} />
                        </Form.Group>


                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableStarRating">
                                <p className="text-left">Star rating</p>
                            </Form.Label>
                            <Form.Control
                                type="number"
                                name="addRentableStarRating"
                                id="addRentableStarRating"
                                ref={addRentableStarRating}
                                step="0.1" />
                        </Form.Group>

                        <Form.Group className="d-flex flex-column align-items-start mb-4">
                            <Form.Label htmlFor="addRentableMapsURL">
                                <p className="text-left">Google maps URL</p>
                            </Form.Label>
                            <Form.Control
                                type="string"
                                name="addRentableMapsURL"
                                id="addRentableMapsURL"
                                ref={addRentableMapsURL} />
                        </Form.Group>



                        <div className="d-flex justify-content-end w-100 mt-3">
                            <button className="btn btn-primary"
                                onClick={POSTRentable}>Submit</button>
                        </div>
                    </Form>
                </Form>
            </Accordion.Body>
        </Accordion.Item>
    )
}

export default AddRentable