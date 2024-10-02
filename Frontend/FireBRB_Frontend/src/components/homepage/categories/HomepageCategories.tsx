import Category from "./Category"
import { RentableType } from "../../../types/entities/rentableType"
import useGetCategoriesToDisplay from "../../hooks/useGetCategoriesToDisplay"


const HomepageCategories = () => {

    const { categories }: { categories: RentableType[] } = useGetCategoriesToDisplay(4)

    const categoriesDisplay = categories.map((rentableType, i) => {
        return (
            <Category key={i} props={rentableType} />
        )
    })

    return (
        <section className="text-start container mb-4 p-0">
            <h3>Browse by category</h3>

            <div className="d-flex justify-content-around row">
                {categoriesDisplay}
            </div>
        </section>
    )
}

export default HomepageCategories