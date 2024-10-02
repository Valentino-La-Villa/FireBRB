import { Rentable } from "../../../types/entities/rentable"
import mapPin from '../../../assets/icons/map-pin.svg'
import globe from '../../../assets/icons/globe.svg'
import { Rating } from "@mui/material"
import { validateImage } from "../../../helpers/imgValidation"
import imgUnavailable from '../../../assets/errors-and-loadingscreens/Img_Unavailable.png'

const Recommendation = ({ props }: { props: Rentable }) => {

    const imgUrl = props?.associatedImgsUrl
    const imgValidation = validateImage(imgUrl) // This is necessary to prevent crashes if the URL pulled from the server doesn't match any images (which could happen if images are taken down from the internet, or by human error when submitting new Rentables from the admin dashboard)

    return (
        <article className="col-12 col-lg-6 d-flex justify-content-center mt-3 mb-5 recommendation">
            <div className="bg-primary rounded-5 w-100">
                <div className="row">
                    <div className="col-6">
                        {imgValidation ?
                            <img alt="" src={imgUrl[0]} className="img-fluid rounded-start-4 w-100" />
                            :
                            <img alt="" src={imgUnavailable} className="img-fluid rounded-start-5 w-100" />
                        }
                    </div>
                    <div className="col-6 py-3 pe-4 d-flex flex-column justify-content-between">
                        <div className="gap-1 d-flex flex-column">
                            <h3><strong>{props?.name}</strong></h3>
                            <p className="text-with-icon"><i>{props?.city} - {props?.region}</i> <img src={mapPin} alt="" /></p>
                            <p className="text-with-icon">{props?.country} <img src={globe} alt="" /></p>
                            <h5 className="mt-2"><span className="price-tag">${props?.pricePerNightUSD.toFixed(2)}</span> <i className="price-tag-text">a night</i></h5>
                        </div>

                        <div className="d-flex justify-content-between pe-2 align-items-center">

                            <div className="d-none d-md-flex d-lg-none d-xl-flex align-items-center gap-2">
                                <div className="bg-white border border-black rounded-1 d-flex align-items-center p-1"><Rating readOnly precision={0.2} value={props.starRating} /></div>
                                <p><i>{props?.starRating?.toFixed(1)}</i></p>
                            </div>

                            <div className="d-flex d-md-none d-lg-flex d-xl-none align-items-center gap-1">
                                <div className="bg-white border border-black rounded-1 d-flex align-items-center p-1 ps-2">
                                    <p><b>{props?.starRating?.toFixed(1)}</b></p>
                                    <Rating readOnly value={1} max={1} />
                                </div>
                            </div>

                            <button className="btn btn-success text-white"><p>Book</p></button>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    )
}

export default Recommendation