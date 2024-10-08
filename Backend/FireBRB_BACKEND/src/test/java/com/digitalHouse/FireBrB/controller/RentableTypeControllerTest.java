package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RentableTypeControllerTest {

    @Autowired
    private IRentableTypeService rentableTypeService;
    @Autowired
    private MockMvc mockMvc;

    private static final String ENDPOINT = "/rentableTypes";
    private static final String NAME = "Hotel";

    public Long dataLoad() throws ResourceNotFoundException {
        RentableTypeDTO input = new RentableTypeDTO(NAME, "imagelink");
        RentableTypeDTO response = rentableTypeService.save(input);

        return response.getId();
    }

    @Test
    @Order(1)
    public void findByIdRequest() throws Exception {
        Long id = dataLoad();

        mockMvc.perform(get(ENDPOINT + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME));
    }

    @Test
    @Order(2)
    public void saveRequest() throws Exception {
        Long id = dataLoad();
        String rentableTypePersisted = "{" +
                "\"associatedImg\": \"imagelink\"," +
                "\"name\": \""+NAME+"\"" +
                "}";

        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rentableTypePersisted)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.associatedImg").value("imagelink"));
    }

    @Test
    @Order(3)
    public void findAllRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @Order(4)
    public void updateRequest() throws Exception {
        Long id = dataLoad();
        String newRentableType = "{" +
                "\"id\": \"1\"," +
                "\"name\": \""+NAME+" - edited\"," +
                "\"associatedImg\": \"imagelink - edited\"" +
                "}";

        mockMvc.perform(put(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRentableType)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME + " - edited"))
                .andExpect(jsonPath("$.associatedImg").value("imagelink - edited"));
    }

    @Test
    @Order(5)
    public void deleteRequest() throws Exception {
        Long id = dataLoad();

        mockMvc.perform(delete(ENDPOINT + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}