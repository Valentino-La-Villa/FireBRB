package com.digitalHouse.FireBrB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rentables")
public class Rentable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String region;
    private String country;
    private Double pricePerNightUSD;
    private Double starRating;
    private List<String> associatedImgsUrl;
    private String googleMapsUrl;

    @ManyToOne
    private RentableType rentableType;

    public Rentable(String name, String address, String city, String region, String country, Double pricePerNightUSD, Double starRating, List<String> associatedImgsUrl, String googleMapsUrl, RentableType rentableType) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.pricePerNightUSD = pricePerNightUSD;
        this.starRating = starRating;
        this.associatedImgsUrl = associatedImgsUrl;
        this.googleMapsUrl = googleMapsUrl;
        this.rentableType = rentableType;
    }
}
