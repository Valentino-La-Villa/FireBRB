package com.digitalHouse.FireBrB.repository;

import com.digitalHouse.FireBrB.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}
