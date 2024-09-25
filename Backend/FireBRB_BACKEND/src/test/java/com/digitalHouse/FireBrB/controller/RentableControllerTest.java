package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RentableControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IRentableService rentableService;
    @Autowired
    private IRentableTypeService typeService;

    private static final String ENDPOINT = "/rentables";
    private static final String ADDRESS = "Montevideo 1423";
    private static final String POSTAL_CODE = "SK1003";
    private static final Double PRICE_PER_NIGHT = 12.41;
    private static final Double PRICE_PER_NIGHT_UPDATE = 21.30;

    public RentableDTO dataLoad() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment");
        RentableTypeDTO rentableTypeResponse = typeService.save(rentableTypeDTO);

        RentableDTO input = new RentableDTO(ADDRESS,
                POSTAL_CODE,
                PRICE_PER_NIGHT,
                rentableTypeResponse.getId());
        RentableDTO response = rentableService.save(input);

        return response;
    }

    @Test
    public void findByIdRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();
        Long id = rentableDTOLoaded.getId();

        mockMvc.perform(get(ENDPOINT + "/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId));
    }

    @Test
    public void saveRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();

        String rentablePersisted = "{" +
                "\"address\": \""+ADDRESS+"\"," +
                "\"postalCode\": \""+POSTAL_CODE+"\"," +
                "\"pricePerNightUSD\": \""+PRICE_PER_NIGHT+"\"," +
                "\"rentableTypeId\": \""+rentableTypeAssociatedId+"\"}";

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(rentablePersisted)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId));
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
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();
        Long id = rentableDTOLoaded.getId();

        String newRentable = "{" +
                "\"id\": \""+id+"\"," +
                "\"address\": \""+ADDRESS+" - edited\"," +
                "\"postalCode\": \""+POSTAL_CODE+" - edited\"," +
                "\"pricePerNightUSD\": \""+PRICE_PER_NIGHT_UPDATE+"\"," +
                "\"rentableTypeId\": \""+rentableTypeAssociatedId+"\"}";

        mockMvc.perform(put(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRentable)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS + " - edited"))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE + " - edited"))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT_UPDATE))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId));
    }

    @Test
    public void deleteRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long id = rentableDTOLoaded.getId();

        mockMvc.perform(delete(ENDPOINT + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}