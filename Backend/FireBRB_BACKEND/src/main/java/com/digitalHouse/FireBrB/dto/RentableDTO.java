package com.digitalHouse.FireBrB.dto;

import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.model.Reservation;

import java.util.List;
import java.util.Set;

public class RentableDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String region;
    private String country;
    private Double pricePerNightUSD;
    private Double starRating;
    private Long rentableTypeId;
    private List<String> associatedImgsUrl;
    private String googleMapsUrl;


    public RentableDTO(String name, String address, String city, String region, String country, Double pricePerNightUSD, Double starRating, Long rentableTypeId, List<String> associatedImgsUrl, String googleMapsUrl) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.pricePerNightUSD = pricePerNightUSD;
        this.starRating = starRating;
        this.rentableTypeId = rentableTypeId;
        this.associatedImgsUrl = associatedImgsUrl;
        this.googleMapsUrl = googleMapsUrl;
    }

    public RentableDTO(Long id, String name, String address, String city, String region, String country, Double pricePerNightUSD, Double starRating, Long rentableTypeId, List<String> associatedImgsUrl, String googleMapsUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.pricePerNightUSD = pricePerNightUSD;
        this.starRating = starRating;
        this.rentableTypeId = rentableTypeId;
        this.associatedImgsUrl = associatedImgsUrl;
        this.googleMapsUrl = googleMapsUrl;
    }

    public RentableDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPricePerNightUSD() {
        return pricePerNightUSD;
    }

    public void setPricePerNightUSD(Double pricePerNightUSD) {
        this.pricePerNightUSD = pricePerNightUSD;
    }

    public Long getRentableTypeId() {
        return rentableTypeId;
    }

    public void setRentableTypeId(Long rentableTypeId) {
        this.rentableTypeId = rentableTypeId;
    }

    public List<String> getAssociatedImgsUrl() {
        return associatedImgsUrl;
    }

    public void setAssociatedImgsUrl(List<String> associatedImgsUrl) {
        this.associatedImgsUrl = associatedImgsUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public String getGoogleMapsUrl() {
        return googleMapsUrl;
    }

    public void setGoogleMapsUrl(String googleMapsUrl) {
        this.googleMapsUrl = googleMapsUrl;
    }
}
