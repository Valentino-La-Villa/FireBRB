import Recommendation from "./Recommendation"
import { Rentable } from "../../../types/entities/rentable"
import useGetRecommendations from "../../hooks/useGetRecommendations"


const HomepageRecommendations = () => {

    const { recommendations }: { recommendations: Rentable[] } = useGetRecommendations(6)

    const recommendationsDisplay = recommendations.map((rentable: Rentable, i) => {
        return <Recommendation key={i} props={rentable} />
    })

    return (
        <section className="text-start container p-0 w-100">
            <h3>Our top picks</h3>
            <div className="col-12 d-flex justify-content-between row w-100">
                {recommendationsDisplay}
            </div>
        </section>
    )
}

export default HomepageRecommendations