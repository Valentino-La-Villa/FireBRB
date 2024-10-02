package com.digitalHouse.FireBrB.service;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.request.FilterRequest;

import java.util.List;
import java.util.Optional;

public interface IRentableService {

    RentableDTO save(RentableDTO rentableDTO) throws ResourceNotFoundException;
    Optional<RentableDTO> findById(Long id) throws ResourceNotFoundException;
    List<RentableDTO> findAll();
    Optional<RentableDTO> findByName(String name);
    RentableDTO update(RentableDTO rentableDTO) throws ResourceNotFoundException;
    Optional<RentableDTO> delete(Long id) throws ResourceNotFoundException;
    List<RentableDTO> filterSearch(FilterRequest filterRequest);
    RentableDTO mapEntityToDTO(Rentable rentable);
    Rentable mapDTOToEntity(RentableDTO rentableDTO) throws ResourceNotFoundException;
}
