package com.digitalHouse.FireBrB.dto;

public class ReservationDTO {

    private Long id;
    private Long userId;
    private Long rentableId;
    private String checkInDate;
    private String checkOutDate;

    public ReservationDTO(Long id, Long userId, Long rentableId, String checkInDate, String checkOutDate) {
        this.id = id;
        this.userId = userId;
        this.rentableId = rentableId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public ReservationDTO(Long userId, Long rentableId, String checkInDate, String checkOutDate) {
        this.userId = userId;
        this.rentableId = rentableId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRentableId() {
        return rentableId;
    }

    public void setRentableId(Long rentableId) {
        this.rentableId = rentableId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
