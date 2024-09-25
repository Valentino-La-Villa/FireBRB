package com.digitalHouse.FireBrB.service;

import com.digitalHouse.FireBrB.dto.ReservationDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    ReservationDTO save(ReservationDTO reservationDTO) throws ResourceNotFoundException;
    Optional<ReservationDTO> findById(Long id) throws ResourceNotFoundException;
    List<ReservationDTO> findAll();
    ReservationDTO update(ReservationDTO reservationDTO) throws ResourceNotFoundException;
    Optional<ReservationDTO> delete(Long id) throws ResourceNotFoundException;
    ReservationDTO mapEntityToDTO(Reservation reservation);
    Reservation mapDTOToEntity(ReservationDTO reservationDTO) throws ResourceNotFoundException;
}
