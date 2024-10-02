package com.digitalHouse.FireBrB.dto;

import com.digitalHouse.FireBrB.model.Rentable;

import java.util.Set;

public class RentableTypeDTO {
    private Long id;
    private String name;
    private String associatedImg;

    public RentableTypeDTO(Long id, String name, String associatedImg) {
        this.id = id;
        this.name = name;
        this.associatedImg = associatedImg;
    }

    public RentableTypeDTO(String name, String associatedImg) {
        this.name = name;
        this.associatedImg = associatedImg;
    }

    public RentableTypeDTO() {
    }

    @Override
    public String toString() {
        return "RentableTypeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociatedImg() {
        return associatedImg;
    }

    public void setAssociatedImg(String associatedImg) {
        this.associatedImg = associatedImg;
    }
}
