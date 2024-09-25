package com.digitalHouse.FireBrB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rentables")
public class Rentable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String postalCode;
    private Double pricePerNightUSD;

    @ManyToOne
    private RentableType rentableType;

    public Rentable(String address, String postalCode, Double pricePerNightUSD, RentableType rentableType) {
        this.address = address;
        this.postalCode = postalCode;
        this.pricePerNightUSD = pricePerNightUSD;
        this.rentableType = rentableType;
    }
}
