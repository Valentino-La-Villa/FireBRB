type PropType = {
    handleSearch: () => void
}

const Searchbar = ({ handleSearch }: PropType) => {
    return (
        <>
            <section>
                <form className="container rounded-3 border border-1 border-dark p-4 my-3 bg-primary">
                    <h2 className="mt-2 mb-4">Your dream vacation is just 5 minutes away</h2>

                    <div className="row d-flex align-items-center justify-content-center row-gap-4">
                        <div className="col-12 col-md-6 col-lg-4 col-xl-3 d-flex align-items-end px-1">
                            <input className="input-group-text w-100 text-start" type="text" placeholder='Where do you fancy visiting?' />
                        </div>
                        <div className="col-12 col-md-2 d-flex align-items-start px-1">
                            <button className="btn-success btn text-white" onClick={handleSearch}>Search</button>
                        </div>
                    </div>
                </form>
            </section>
        </>
    )
}

export default Searchbar