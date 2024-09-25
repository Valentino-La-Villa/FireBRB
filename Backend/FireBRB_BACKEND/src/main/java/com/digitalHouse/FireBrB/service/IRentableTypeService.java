package com.digitalHouse.FireBrB.service;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.model.RentableType;

import java.util.List;
import java.util.Optional;

public interface IRentableTypeService {

    RentableTypeDTO save(RentableTypeDTO rentableTypeDTO);
    Optional<RentableTypeDTO> findById(Long id) throws ResourceNotFoundException;
    List<RentableTypeDTO> findAll();
    RentableTypeDTO update(RentableTypeDTO rentableTypeDTO) throws ResourceNotFoundException;
    Optional<RentableTypeDTO> delete(Long id) throws ResourceNotFoundException;
    RentableTypeDTO mapEntityToDTO(RentableType rentableType);
    RentableType mapDTOToEntity(RentableTypeDTO rentableTypeDTO);
}
