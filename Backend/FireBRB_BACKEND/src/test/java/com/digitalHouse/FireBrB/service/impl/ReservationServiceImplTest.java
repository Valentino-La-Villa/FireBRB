package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.dto.ReservationDTO;
import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceImplTest {

    @Autowired
    private RentableServiceImpl rentableService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RentableTypeServiceImpl rentableTypeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReservationServiceImpl reservationService;

    public Long loadData() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Cabin");
        RentableTypeDTO rentableTypeResponse = rentableTypeService.save(rentableTypeDTO);

        RentableDTO rentableDTO = new RentableDTO("Viamonte 4150", "JK2004", 6.43, rentableTypeResponse.getId());
        var user = User.builder()
                .firstName("Juan")
                .surname("Pérez")
                .email("Juancito200@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .role(Role.USERREGULAR)
                .build();
        User userOutput = userRepository.save(user);
        RentableDTO rentableResponse = rentableService.save(rentableDTO);

        ReservationDTO input = new ReservationDTO(userOutput.getId(), rentableResponse.getId(), "2022-10-10", "2022-11-11");
        ReservationDTO response = reservationService.save(input);

        return response.getId();
    }

    @Test
    public void save() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment");
        RentableTypeDTO rentableTypeResponse = rentableTypeService.save(rentableTypeDTO);

        RentableDTO rentableDTO = new RentableDTO("Sarmiento 345", "MFD3004", 23.72, rentableTypeResponse.getId());
        var user = User.builder()
                .firstName("Fisi")
                .surname("Rodriguez")
                .email("Ferensibiditoilet@gmail.com")
                .password(passwordEncoder.encode("5678"))
                .role(Role.USERREGULAR)
                .build();

        User userOutput = userRepository.save(user);
        RentableDTO rentableResponse = rentableService.save(rentableDTO);

        ReservationDTO input = new ReservationDTO(userOutput.getId(), rentableResponse.getId(), "2024-03-03", "2024-04-04");
        ReservationDTO response = reservationService.save(input);

        Optional<ReservationDTO> actualReservation = reservationService.findById(response.getId());

        assertThat(actualReservation.get())
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    public void findById() throws ResourceNotFoundException {
        Long id = loadData();
        Optional<ReservationDTO> actualReservation = reservationService.findById(id);
        assertNotNull(actualReservation.get());
    }

    @Test
    public void findAll() throws ResourceNotFoundException {
        loadData();
        List<ReservationDTO> response = reservationService.findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    public void update() throws ResourceNotFoundException {
        Long id = loadData();
        ReservationDTO input = new ReservationDTO(id, 1L, 1L, "2019-10-10", "2020-10-10");
        ReservationDTO response = reservationService.update(input);

        assertThat(input)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    public void delete() throws ResourceNotFoundException {
        Long id = loadData();
        reservationService.delete(id);
        Optional<ReservationDTO> actual;
        try {
            actual = reservationService.findById(id);
        } catch(ResourceNotFoundException e) {
            assertTrue(true); // I don't know as of right now if there is a more elegant way to make a JUnit check for errorType, given the fact that if I straight up check for actual.isPresent() the test will not pass, because it immediately throws ResourceNotFoundException
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}