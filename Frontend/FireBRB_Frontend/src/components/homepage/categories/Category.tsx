import { RentableType } from "../../../types/entities/rentableType"


const Category = ({ props }: { props: RentableType }) => {

    const imgUrl = [props.associatedImg]

    return (
        <div className="col-12 col-sm-6 col-lg-3 p-3 rentable-type-display position-relative">
            <div className="rounded-4 text-white border-black border-1 border h-100 bg-success">
                <img
                    className="rounded-top-4"
                    src={imgUrl[0]}
                    alt="" />
                <h6 className="m-2">{props.name + "s"}</h6>
            </div>
        </div>
    )
}

export default Category
