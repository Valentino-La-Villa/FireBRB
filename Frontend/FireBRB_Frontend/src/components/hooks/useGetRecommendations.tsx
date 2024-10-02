import { useEffect } from 'react'
import { findAllFromAPI } from '../../redux/slices/rentablesSlice'
import { useAppDispatch, useAppSelector } from '../../redux/store'
import { shuffleArray } from '../../helpers/shuffleArray'

const useGetRecommendations = (max: number) => {

    const state = useAppSelector(state => state.rentables.localStoredRentables)
    const dispatch = useAppDispatch()

    useEffect(() => {
        if (state.data.length == 0) {
            dispatch(findAllFromAPI())
        }
    }, [])

    const randomizedRentablesArray = shuffleArray(state.data)

    const recommendations = randomizedRentablesArray.slice(0, max)

    return {
        recommendations: recommendations
    }
}

export default useGetRecommendations