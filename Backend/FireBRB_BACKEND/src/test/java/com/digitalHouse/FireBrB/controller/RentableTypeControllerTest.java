package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.junit.jupiter.api.Test;
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
class RentableTypeControllerTest {

    @Autowired
    private IRentableTypeService rentableTypeService;
    @Autowired
    private MockMvc mockMvc;

    private static final String ENDPOINT = "/rentableTypes";
    private static final String NAME = "Appartment";

    public Long dataLoad() throws ResourceNotFoundException {
        RentableTypeDTO input = new RentableTypeDTO(NAME);
        RentableTypeDTO response = rentableTypeService.save(input);

        return response.getId();
    }

    @Test
    public void findByIdRequest() throws Exception {
        Long id = dataLoad();

        mockMvc.perform(get(ENDPOINT + "/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME));
    }

    @Test
    public void saveRequest() throws Exception {
        Long id = dataLoad();
        String rentableTypePersisted = "{\"name\": \""+NAME+"\"}";

        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rentableTypePersisted)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME));
    }

    @Test
    public void findAllRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void updateRequest() throws Exception {
        Long id = dataLoad();
        String newRentableType = "{" +
                "\"id\": \"1\"," +
                "\"name\": \""+NAME+" - edited\"" +
                "}";

        mockMvc.perform(put(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRentableType)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(NAME + " - edited"));
    }

    @Test
    public void deleteRequest() throws Exception {
        Long id = dataLoad();

        mockMvc.perform(delete(ENDPOINT + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}