
export const validateImage = (imgArray: string[]): boolean => {
    if (imgArray != null) {
        return (imgArray.length >= 1)
    }
    else return false
}