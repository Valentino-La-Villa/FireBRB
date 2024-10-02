package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.dto.ReservationDTO;
import com.digitalHouse.FireBrB.enums.Role;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.User;
import com.digitalHouse.FireBrB.repository.IUserRepository;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import com.digitalHouse.FireBrB.service.IReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IRentableService rentableService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IRentableTypeService typeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String ENDPOINT = "/reservations";
    private final static String CHECK_IN = "2022-10-10";
    private final static String CHECK_OUT = "2023-11-11";
    private final static String CHECK_IN_UPDATE = "1022-09-09";
    private final static String CHECK_OUT_UPDATE = "3022-01-01";

    public ReservationDTO dataLoad() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment", "");
        RentableTypeDTO rentableTypeResponse = typeService.save(rentableTypeDTO);

        RentableDTO rentableDTO = new RentableDTO("Casucha", "Entre Ríos 1545", "Morón", "Buenos Aires", "Argentina", 12.51, 4.0, 1L, new ArrayList<>(), "");
        RentableDTO rentableResponse = rentableService.save(rentableDTO);

        var user = User.builder()
                .firstName("Juan")
                .surname("Pérez")
                .email("Juancito200@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .role(Role.USERREGULAR)
                .build();
        User userOutput = userRepository.save(user);

        ReservationDTO input = new ReservationDTO(userOutput.getId(), rentableResponse.getId(), CHECK_IN, CHECK_OUT);
        ReservationDTO response = reservationService.save(input);

        return response;
    }

    @Test
    public void findByIdRequest() throws Exception {
        ReservationDTO reservationDTOLoaded = dataLoad();
        Long rentableAssociatedId = reservationDTOLoaded.getRentableId();
        Long userAssociatedId = reservationDTOLoaded.getUserId();
        Long id = reservationDTOLoaded.getId();

        mockMvc.perform(get(ENDPOINT + "/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userAssociatedId))
                .andExpect(jsonPath("$.rentableId").value(rentableAssociatedId))
                .andExpect(jsonPath("$.checkInDate").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOutDate").value(CHECK_OUT));
    }

    @Test
    public void saveRequest() throws Exception {
        ReservationDTO reservationDTOLoaded = dataLoad();
        Long rentableAssociatedId = reservationDTOLoaded.getRentableId();
        Long userAssociatedId = reservationDTOLoaded.getUserId();
        Long id = reservationDTOLoaded.getId();

        String reservationPersisted = "{" +
                "\"userId\": \""+userAssociatedId+"\"," +
                "\"rentableId\": \""+rentableAssociatedId+"\"," +
                "\"checkInDate\": \""+CHECK_IN+"\"," +
                "\"checkOutDate\": \""+CHECK_OUT+"\"}";

        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservationPersisted)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userAssociatedId))
                .andExpect(jsonPath("$.rentableId").value(rentableAssociatedId))
                .andExpect(jsonPath("$.checkInDate").value(CHECK_IN))
                .andExpect(jsonPath("$.checkOutDate").value(CHECK_OUT));
    }

    @Test
    public void findAllRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void updateRequest() throws Exception {
        ReservationDTO reservationDTOLoaded = dataLoad();
        Long rentableAssociatedId = reservationDTOLoaded.getRentableId();
        Long userAssociatedId = reservationDTOLoaded.getUserId();
        Long id = reservationDTOLoaded.getId();

        String newRentable = "{" +
                "\"id\": \""+id+"\"," +
                "\"userId\": \""+userAssociatedId+"\"," +
                "\"rentableId\": \""+rentableAssociatedId+"\"," +
                "\"checkInDate\": \""+CHECK_IN_UPDATE+"\"," +
                "\"checkOutDate\": \""+CHECK_OUT_UPDATE+"\"}";

        mockMvc.perform(put(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRentable)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userAssociatedId))
                .andExpect(jsonPath("$.rentableId").value(rentableAssociatedId))
                .andExpect(jsonPath("$.checkInDate").value(CHECK_IN_UPDATE))
                .andExpect(jsonPath("$.checkOutDate").value(CHECK_OUT_UPDATE));
    }

    @Test
    public void deleteRequest() throws Exception {
        ReservationDTO reservationDTOLoaded = dataLoad();
        Long id = reservationDTOLoaded.getId();

        mockMvc.perform(delete(ENDPOINT + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}