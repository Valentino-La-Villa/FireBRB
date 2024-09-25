package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.auth.AuthenticationService;
import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.ReservationDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.RsaAlgorithm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private IReservationService reservationService;
    private IRentableService rentableService;
    private IUserRepository userRepository;

    @Autowired
    public ReservationController(IReservationService reservationService, IRentableService rentableService, IUserRepository userRepository) {
        this.reservationService = reservationService;
        this.rentableService = rentableService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservationDTO) throws ResourceNotFoundException {
        ResponseEntity<ReservationDTO> response;

        Optional<User> associatedUser = userRepository.findById(reservationDTO.getUserId());
        System.out.println(reservationDTO.getRentableId());
        Optional<RentableDTO> associatedRentable = rentableService.findById(reservationDTO.getRentableId());

        if (associatedRentable.isPresent() && associatedUser.isPresent()) {
            response = ResponseEntity.ok(reservationService.save(reservationDTO));
        } else response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return response;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<ReservationDTO> requestedReservation = reservationService.findById(id);

        if (requestedReservation.isPresent()) {
            return ResponseEntity.ok(requestedReservation.get());
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<ReservationDTO> update(@RequestBody ReservationDTO reservationDTO) throws ResourceNotFoundException {
        ResponseEntity<ReservationDTO> response;

        // Checking if all dependencies exist first
        Optional<RentableDTO> associatedRentable = rentableService.findById(reservationDTO.getRentableId());
        Optional<User> associatedUser = userRepository.findById(reservationDTO.getUserId());

        if (associatedRentable.isPresent() && associatedUser.isPresent()) {
            response = ResponseEntity.ok(reservationService.update(reservationDTO));
        } else response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        reservationService.delete(id);
        return ResponseEntity.ok("Reservation Controller: Deleted Reservation with ID=" + id);
    }


}
