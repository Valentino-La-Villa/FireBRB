package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RentableTypeServiceImplTest {

    @Autowired
    private RentableTypeServiceImpl rentableTypeService;

    public Long loadData() {
        RentableTypeDTO initialData = new RentableTypeDTO("Cabin", "");
        RentableTypeDTO response = rentableTypeService.save(initialData);
        return response.getId();
    }

    @Test
    @Order(1)
    public void save() throws ResourceNotFoundException {
        RentableTypeDTO initialData = new RentableTypeDTO("Appartment", "");
        RentableTypeDTO response = rentableTypeService.save(initialData);

        Optional<RentableTypeDTO> actualRentableType = rentableTypeService.findById(response.getId());

        assertThat(actualRentableType.get())
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    @Order(2)
    public void findById() throws ResourceNotFoundException {
        Optional<RentableTypeDTO> actualRentableType = rentableTypeService.findById(1L);
        assertNotNull(actualRentableType.get());
    }

    @Test
    @Order(3)
    public void findAll() {
        List<RentableTypeDTO> response = rentableTypeService.findAll();
        assertFalse(response.isEmpty());
    }

    @Test
    @Order(4)
    public void update() throws ResourceNotFoundException {
        RentableTypeDTO input = new RentableTypeDTO(1L, "Penthouse", "");
        RentableTypeDTO response = rentableTypeService.update(input);

        assertThat(input)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @Test
    @Order(5)
    public void delete() throws ResourceNotFoundException {
        Long id = loadData();
        rentableTypeService.delete(id);
        Optional<RentableTypeDTO> actual;
        try {
            actual = rentableTypeService.findById(id);
        } catch(ResourceNotFoundException e) {
            assertTrue(true); // I don't know as of right now if there is a more elegant way to make a JUnit check for errorType, given the fact that if I straight up check for actual.isPresent() the test will not pass, because it immediately throws ResourceNotFoundException
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}