package com.digitalHouse.FireBrB.dto;

import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.model.Reservation;

import java.util.List;
import java.util.Set;

public class RentableDTO {
    private Long id;
    private String address;
    private String postalCode;
    private Double pricePerNightUSD;
    private Long rentableTypeId;
    private List<String> associatedImgsUrl;

    public RentableDTO(Long id, String address, String postalCode, Double pricePerNightUSD, Long rentableTypeId, List<String> associatedImgsUrl) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.pricePerNightUSD = pricePerNightUSD;
        this.rentableTypeId = rentableTypeId;
        this.associatedImgsUrl = associatedImgsUrl;
    }

    public RentableDTO(String address, String postalCode, Double pricePerNightUSD, Long rentableTypeId, List<String> associatedImgsUrl) {
        this.address = address;
        this.postalCode = postalCode;
        this.pricePerNightUSD = pricePerNightUSD;
        this.rentableTypeId = rentableTypeId;
        this.associatedImgsUrl = associatedImgsUrl;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
}
