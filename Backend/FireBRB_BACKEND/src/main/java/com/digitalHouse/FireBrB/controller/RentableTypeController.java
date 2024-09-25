package com.digitalHouse.FireBrB.controller;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rentableTypes")
public class RentableTypeController {

    private IRentableTypeService rentableTypeService;

    @Autowired
    public RentableTypeController(IRentableTypeService rentableTypeService) {
        this.rentableTypeService = rentableTypeService;
    }

    @PostMapping
    public ResponseEntity<RentableTypeDTO> save(@RequestBody RentableTypeDTO rentableTypeDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(rentableTypeService.save(rentableTypeDTO));
    }

    @GetMapping
    public ResponseEntity<List<RentableTypeDTO>> findAll() {
        return ResponseEntity.ok(rentableTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentableTypeDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<RentableTypeDTO> rentableTypeRequested = rentableTypeService.findById(id);

        if (rentableTypeRequested.isPresent()) {
            return ResponseEntity.ok(rentableTypeRequested.get());
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<RentableTypeDTO> update(@RequestBody RentableTypeDTO rentableTypeDTO) throws ResourceNotFoundException {
        Optional<RentableTypeDTO> rentableTypeRequested = rentableTypeService.findById(rentableTypeDTO.getId());

        if (rentableTypeRequested.isPresent()) {
            return ResponseEntity.ok(rentableTypeService.update(rentableTypeDTO));
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        rentableTypeService.delete(id);
        return ResponseEntity.ok("Rentable Type Controller: Deleted Rentable Type with ID=" + id);
    }
}
