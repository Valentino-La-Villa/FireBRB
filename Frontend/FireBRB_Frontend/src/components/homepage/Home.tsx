import HomepageCategories from "./categories/HomepageCategories";
import HomepageRecommendations from "./recommendations/HomepageRecommendations";
import Searchbar from "./Searchbar";

export default function Home() {
    return (
        <main className="container-fluid">
            <Searchbar
                handleSearch={() => { }} />

            <HomepageCategories />

            <HomepageRecommendations />
        </main>
    )
}