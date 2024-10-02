package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.request.FilterRequest;
import com.digitalHouse.FireBrB.service.IRentableService;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentables")
public class RentableController {

    private IRentableService rentableService;
    private IRentableTypeService rentableTypeService;

    @Autowired
    public RentableController(IRentableService rentableService, IRentableTypeService rentableTypeService) {
        this.rentableService = rentableService;
        this.rentableTypeService = rentableTypeService;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody RentableDTO rentableDTO) throws ResourceNotFoundException {

        // Checking if all dependencies exist first
        if (rentableTypeService.findById(rentableDTO.getRentableTypeId()).isPresent()) {
            Optional<RentableDTO> optionalRentableDTO = rentableService.findByName(rentableDTO.getName());
            if (optionalRentableDTO.isPresent()) {
                return ResponseEntity.badRequest().body("A Rentable with the name '" + rentableDTO.getName() + "' already exists");
            }
            else return ResponseEntity.ok(rentableService.save(rentableDTO));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public ResponseEntity<List<RentableDTO>> findAll() {
        return ResponseEntity.ok(rentableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentableDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<RentableDTO> rentableDTORequested = rentableService.findById(id);

        if (rentableDTORequested.isPresent()) {
            return ResponseEntity.ok(rentableDTORequested.get());
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RentableDTO>> filterSearch(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDirection,
            @RequestParam(required = false) Boolean random
    ) {

        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setLimit(limit != null ? limit: 20);
        filterRequest.setSortBy(sortBy);
        filterRequest.setSortDirection(sortDirection);
        filterRequest.setRandom(random != null && random);

        List<RentableDTO> rentablesToReturn = rentableService.filterSearch(filterRequest);
        return ResponseEntity.ok(rentablesToReturn);
    }

    @PutMapping
    public ResponseEntity<RentableDTO> update(@RequestBody RentableDTO rentableDTO) throws ResourceNotFoundException {
        ResponseEntity<RentableDTO> response;

        // Checking for dependencies first
        if (rentableTypeService.findById(rentableDTO.getRentableTypeId()).isPresent()) {
            response = ResponseEntity.ok(rentableService.save(rentableDTO));
        } else response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        rentableService.delete(id);
        return ResponseEntity.ok("Rentable controller: Deleted Rentable with ID=" + id);
    }

}
