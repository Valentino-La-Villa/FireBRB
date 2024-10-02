import { useEffect } from "react"
import { useAppDispatch, useAppSelector } from "../../redux/store"
import { findAllRentableTypesFromAPI } from "../../redux/slices/rentablesSlice"

const useGetCategoriesToDisplay = (max: number) => {

    const categorieState = useAppSelector(state => state.rentables.localStoredRentableTypes)
    const dispatch = useAppDispatch()

    useEffect(() => {
        if (categorieState.data.length == 0) {
            dispatch(findAllRentableTypesFromAPI())
        }
    }, [])

    const categories = categorieState.data.slice(0, max)

    return {
        categories: categories
    }
}

export default useGetCategoriesToDisplay