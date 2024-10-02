export function shuffleArray<T>(arr: T[]): T[] {

    const response = [...arr]

    if (response.length > 0) {
        for (let i = response.length - 1; i >= 0; i--) {
            let j = Math.floor(Math.random() * (i + 1))
            const temp = response[i]
            response[i] = response[j]
            response[j] = temp
        }
        return response
    }
    else return []
}