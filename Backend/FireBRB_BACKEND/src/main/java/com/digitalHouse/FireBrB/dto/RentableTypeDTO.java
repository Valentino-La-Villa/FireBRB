package com.digitalHouse.FireBrB.dto;

import com.digitalHouse.FireBrB.model.Rentable;

import java.util.Set;

public class RentableTypeDTO {
    private Long id;
    private String name;

    public RentableTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RentableTypeDTO(String name) {
        this.name = name;
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
}
