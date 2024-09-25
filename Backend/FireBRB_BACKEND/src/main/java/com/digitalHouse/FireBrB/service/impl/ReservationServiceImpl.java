package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.ReservationDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.model.Reservation;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IRentableRepository;
import com.digitalHouse.FireBrB.repository.IReservationRepository;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements IReservationService {

    private IUserRepository userRepository;
    private IRentableRepository rentableRepository;
    private IReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(IUserRepository userRepository, IRentableRepository rentableRepository, IReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.rentableRepository = rentableRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) throws ResourceNotFoundException {
        Reservation reservation = mapDTOToEntity(reservationDTO);
        Reservation actualReservation = reservationRepository.save(reservation);

        ReservationDTO response = mapEntityToDTO(actualReservation);
        return response;
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservationRequested = reservationRepository.findById(id);
        Optional<ReservationDTO> responseDTO = null;

        if (optionalReservationRequested.isPresent()) {
            // Mapping the rentableType found in the database back to the DTO
            Reservation reservationRequested = optionalReservationRequested.get();

            ReservationDTO reservationDTORequested = mapEntityToDTO(reservationRequested);

            responseDTO = Optional.of(reservationDTORequested);
        }
        else throw new ResourceNotFoundException("Reservation Service: Could not find Reservation Type with ID=" + id);
        return responseDTO;
    }

    @Override
    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> response = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = mapEntityToDTO(reservation);
            response.add(reservationDTO);
        }
        return response;
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDTO) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservationRequested = reservationRepository.findById(reservationDTO.getId());

        if (optionalReservationRequested.isPresent()) {
            Reservation reservationRequested = optionalReservationRequested.get();
            reservationRequested = mapDTOToEntity(reservationDTO);

            reservationRepository.save(reservationRequested);

            ReservationDTO response = mapEntityToDTO(reservationRequested);

            return response;
        }
        else throw new ResourceNotFoundException("Reservation Service: Could not update Reservation with ID=" + reservationDTO.getId());
    }

    @Override
    public Optional<ReservationDTO> delete(Long id) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservationRequested = reservationRepository.findById(id);
        Optional<ReservationDTO> response;

        if (optionalReservationRequested.isPresent()) {
            Reservation reservationRequested = optionalReservationRequested.get();
            ReservationDTO responseValue = mapEntityToDTO(reservationRequested);

            response = Optional.of(responseValue);
            reservationRepository.deleteById(id);
            return response;
        }
        else throw new ResourceNotFoundException("Rentable Service: Could not delete Rentable with ID=" + id);
    }

    @Override
    public ReservationDTO mapEntityToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId(reservation.getId());
        reservationDTO.setCheckInDate(reservation.getCheckInDate().toString());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate().toString());
        reservationDTO.setUserId(reservation.getUser().getId());
        reservationDTO.setRentableId(reservation.getRentable().getId());

        return reservationDTO;
    }

    @Override
    public Reservation mapDTOToEntity(ReservationDTO reservationDTO) throws ResourceNotFoundException {
        Reservation reservation = new Reservation();
        Optional<User> associatedUser = userRepository.findById(reservationDTO.getUserId());
        Optional<Rentable> associatedRentable = rentableRepository.findById(reservationDTO.getRentableId());

        if (associatedUser.isPresent()) {
            if (associatedRentable.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                reservation.setId(reservationDTO.getId());
                reservation.setUser(associatedUser.get());
                reservation.setRentable(associatedRentable.get());
                reservation.setCheckInDate(LocalDate.parse(reservationDTO.getCheckInDate(), formatter));
                reservation.setCheckOutDate(LocalDate.parse(reservationDTO.getCheckOutDate(), formatter));
            } else throw new ResourceNotFoundException("Reservation Service: Could not find Rentable with ID=" + reservationDTO.getRentableId());
        } else throw new ResourceNotFoundException("Reservation Service: Could not find User with ID=" + reservationDTO.getUserId());

        return reservation;
    }
}
