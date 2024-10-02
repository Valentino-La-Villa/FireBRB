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
public class RentableType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String associatedImg;

    public RentableType(String name, String associatedImg) {
        this.name = name;
        this.associatedImg = associatedImg;
    }
}
