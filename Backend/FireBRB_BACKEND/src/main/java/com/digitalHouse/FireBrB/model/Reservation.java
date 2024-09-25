package com.digitalHouse.FireBrB.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Rentable rentable;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(User user, Rentable rentable, LocalDate checkInDate, LocalDate checkOutDate) {
        this.user = user;
        this.rentable = rentable;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
