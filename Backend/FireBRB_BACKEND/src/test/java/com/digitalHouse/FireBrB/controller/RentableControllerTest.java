package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.service.IRentableService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    private static final String IMAGE_1 = "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_2 = "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_3 = "https://plus.unsplash.com/premium_photo-1687960116497-0dc41e1808a2?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_4 = "https://images.unsplash.com/photo-1529290130-4ca3753253ae?q=80&w=2076&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMGSJSON = "[\"" + IMAGE_1 + "\", \"" + IMAGE_2 + "\"]";

    public RentableDTO dataLoad() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment");
        RentableTypeDTO rentableTypeResponse = typeService.save(rentableTypeDTO);


        List<String> associatedImgs = new ArrayList<>();
        associatedImgs.add(IMAGE_1);
        associatedImgs.add(IMAGE_2);

        RentableDTO input = new RentableDTO(ADDRESS,
                POSTAL_CODE,
                PRICE_PER_NIGHT,
                rentableTypeResponse.getId(),
                associatedImgs
                );
        RentableDTO response = rentableService.save(input);

        return response;
    }

    @Test
    @Order(3)
    public void findByIdRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();
        Long id = rentableDTOLoaded.getId();

        mockMvc.perform(get(ENDPOINT + "/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId))
                .andExpect(jsonPath("$.associatedImgsUrl").isNotEmpty());
    }

    @Test
    @Order(2)
    public void saveRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();

        String rentablePersisted = "{" +
                "\"address\": \""+ADDRESS+"\"," +
                "\"postalCode\": \""+POSTAL_CODE+"\"," +
                "\"pricePerNightUSD\": \""+PRICE_PER_NIGHT+"\"," +
                "\"rentableTypeId\": \""+rentableTypeAssociatedId+"\"," +
                "\"associatedImgsUrl\":" + IMGSJSON +
                "}";

        mockMvc.perform(post(ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(rentablePersisted)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId))
                .andExpect(jsonPath("$.associatedImgsUrl").isNotEmpty());
    }

    @Test
    @Order(1)
    public void findAllRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @Order(4)
    public void updateRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long rentableTypeAssociatedId = rentableDTOLoaded.getRentableTypeId();
        Long id = rentableDTOLoaded.getId();

        String newRentable = "{" +
                "\"id\": \""+id+"\"," +
                "\"address\": \""+ADDRESS+" - edited\"," +
                "\"postalCode\": \""+POSTAL_CODE+" - edited\"," +
                "\"pricePerNightUSD\": \""+PRICE_PER_NIGHT_UPDATE+"\"," +
                "\"rentableTypeId\": \""+rentableTypeAssociatedId+"\"," +
                "\"associatedImgsUrl\":" + IMGSJSON +
                "}";

        mockMvc.perform(put(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newRentable)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value(ADDRESS + " - edited"))
                .andExpect(jsonPath("$.postalCode").value(POSTAL_CODE + " - edited"))
                .andExpect(jsonPath("$.pricePerNightUSD").value(PRICE_PER_NIGHT_UPDATE))
                .andExpect(jsonPath("$.rentableTypeId").value(rentableTypeAssociatedId))
                .andExpect(jsonPath("$.associatedImgsUrl").isNotEmpty());
    }

    @Test
    @Order(5)
    public void deleteRequest() throws Exception {
        RentableDTO rentableDTOLoaded = dataLoad();
        Long id = rentableDTOLoaded.getId();

        mockMvc.perform(delete(ENDPOINT + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}