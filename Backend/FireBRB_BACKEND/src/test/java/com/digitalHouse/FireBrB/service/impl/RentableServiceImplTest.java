package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.request.FilterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentableServiceImplTest {

    @Autowired
    private RentableServiceImpl rentableService;
    @Autowired
    private RentableTypeServiceImpl typeService;

    private static final String IMAGE_1 = "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_2 = "https://images.unsplash.com/photo-1444201983204-c43cbd584d93?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_3 = "https://plus.unsplash.com/premium_photo-1687960116497-0dc41e1808a2?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";
    private static final String IMAGE_4 = "https://images.unsplash.com/photo-1529290130-4ca3753253ae?q=80&w=2076&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D";

    public Long loadData() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment", "");
        RentableTypeDTO rentableTypeResponse = typeService.save(rentableTypeDTO);

        List<String> associatedImgs = new ArrayList<>();
        associatedImgs.add(IMAGE_1);
        associatedImgs.add(IMAGE_2);


        RentableDTO input = new RentableDTO();
        input.setAddress("Montevideo 1423");
        input.setCity("Rosario");
        input.setRegion("Santa Fe");
        input.setCountry("Argentina");
        input.setPricePerNightUSD(12.41);
        input.setStarRating(0.5);
        input.setRentableTypeId(rentableTypeResponse.getId());
        input.setAssociatedImgsUrl(associatedImgs);
        RentableDTO response = rentableService.save(input);

        return response.getId();
    }

    @Test
    public void save() throws ResourceNotFoundException {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO("Appartment", "");
        RentableTypeDTO rentableTypeResponse = typeService.save(rentableTypeDTO);

        List<String> associatedImgs = new ArrayList<>();
        associatedImgs.add(IMAGE_1);
        associatedImgs.add(IMAGE_2);

        RentableDTO input = new RentableDTO();
        input.setAddress("Montevideo 1423");
        input.setCity("Rosario");
        input.setRegion("Santa Fe");
        input.setCountry("Argentina");
        input.setPricePerNightUSD(12.41);
        input.setStarRating(0.5);
        input.setRentableTypeId(rentableTypeResponse.getId());
        input.setAssociatedImgsUrl(associatedImgs);
        RentableDTO response = rentableService.save(input);

        Optional<RentableDTO> actualRentable = rentableService.findById(response.getId());

        assertThat(actualRentable.get())
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    public void findById() throws ResourceNotFoundException {
        Long id = loadData();
        Optional<RentableDTO> actualRentable = rentableService.findById(id);
        assertNotNull(actualRentable.get());
    }

    @Test
    public void findAll() throws ResourceNotFoundException {
        loadData();
        List<RentableDTO> response = rentableService.findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    public void filterSearch() throws ResourceNotFoundException {
        loadData();
        List<RentableDTO> response = rentableService.filterSearch(new FilterRequest(3, true));
        assertEquals(response.size(), 3);
    }

    @Test
    public void update() throws ResourceNotFoundException {
        Long id = loadData();


        List<String> associatedImgs = new ArrayList<>();
        associatedImgs.add(IMAGE_3);
        associatedImgs.add(IMAGE_4);

        RentableDTO input = new RentableDTO(id, "Residencia Márquez", "Crespo 2547", "Rosario", "Santa Fe", "Argentina", 10.91, 3.5, 1L, associatedImgs, "");
        RentableDTO response = rentableService.update(input);

        assertThat(input)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    public void delete() throws ResourceNotFoundException {
        Long id = loadData();
        rentableService.delete(id);
        Optional<RentableDTO> actual;
        try {
            actual = rentableService.findById(id);
        } catch(ResourceNotFoundException e) {
            assertTrue(true); // I don't know as of right now if there is a more elegant way to make a JUnit check for errorType, given the fact that if I straight up check for actual.isPresent() the test will not pass, because it immediately throws ResourceNotFoundException
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
