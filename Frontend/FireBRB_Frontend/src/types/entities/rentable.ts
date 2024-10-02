export type Rentable = {
    id: number,
    name: string,
    address: string,
    city: string,
    region: string,
    country: string,
    pricePerNightUSD: number,
    rentableTypeId: number,
    associatedImgsUrl: string[],
    starRating: number
}