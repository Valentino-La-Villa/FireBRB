export type RentableRequest = {
    name: string,
    address: string,
    city: string,
    region: string,
    country: string,
    pricePerNightUSD: number,
    rentableTypeId: number,
    associatedImgsURL: string[],
    starRating: number
}