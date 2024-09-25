package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.repository.IRentableTypeRepository;
import com.digitalHouse.FireBrB.service.IRentableTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentableTypeServiceImpl implements IRentableTypeService {

    private IRentableTypeRepository rentableTypeRepository;

    @Autowired
    public RentableTypeServiceImpl(IRentableTypeRepository rentableTypeRepository) {
        this.rentableTypeRepository = rentableTypeRepository;
    }
//
//    public Set<Long> parseRentableList(Set<Rentable> rentableSet) {
//        Set<Long> parsedRentableSet = new HashSet<>();
//        if (!rentableSet.isEmpty()) {
//            for (Rentable rentable : rentableSet) {
//                parsedRentableSet.add(rentable.getId());
//            }
//            return parsedRentableSet;
//        }
//        else return new HashSet<>();
//    }

    @Override
    public RentableTypeDTO save(RentableTypeDTO rentableTypeDTO) {
        Set<Rentable> startingRentables = new HashSet<>();

        // Mapping the DTO to the entity
        RentableType rentableType = mapDTOToEntity(rentableTypeDTO);

        rentableTypeRepository.save(rentableType);

        // Returning a DTO with the saved data
        RentableTypeDTO responseDTO = mapEntityToDTO(rentableType);

        return responseDTO;
    }

    @Override
    public Optional<RentableTypeDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<RentableType> optionalRentableTypeRequested = rentableTypeRepository.findById(id);
        Optional<RentableTypeDTO> responseDTO = null;

        if (optionalRentableTypeRequested.isPresent()) {
            // Mapping the rentableType found in the database back to the DTO
            RentableType rentableTypeRequested = optionalRentableTypeRequested.get();

            RentableTypeDTO rentableTypeDTORequested = mapEntityToDTO(rentableTypeRequested);

            responseDTO = Optional.of(rentableTypeDTORequested);
        }
        else throw new ResourceNotFoundException("Rentable Type Service: Could not find Rentable Type with ID=" + id);
        return responseDTO;
    }

    @Override
    public List<RentableTypeDTO> findAll() {
        List<RentableType> rentableTypes = rentableTypeRepository.findAll();
        List<RentableTypeDTO> response = new ArrayList<>();

        for (RentableType rentableType : rentableTypes) {
            RentableTypeDTO rentableTypeDTO = mapEntityToDTO(rentableType);
            response.add(rentableTypeDTO);
        }
        return response;
    }

    @Override
    public RentableTypeDTO update(RentableTypeDTO rentableTypeDTO) throws ResourceNotFoundException {
        Optional<RentableType> optionalRentableTypeRequested = rentableTypeRepository.findById(rentableTypeDTO.getId());

        if (optionalRentableTypeRequested.isPresent()) {
            RentableType rentableTypeRequested = mapDTOToEntity(rentableTypeDTO);

            rentableTypeRepository.save(rentableTypeRequested);

            RentableTypeDTO response = mapEntityToDTO(rentableTypeRequested);

            return response;
        }
        else throw new ResourceNotFoundException("Rentable Type Service: Could not update Rentable Type with ID=" + rentableTypeDTO.getId());
    }

    @Override
    public Optional<RentableTypeDTO> delete(Long id) throws ResourceNotFoundException {
        Optional<RentableType> optionalRentableTypeRequested = rentableTypeRepository.findById(id);
        Optional<RentableTypeDTO> response;

        if (optionalRentableTypeRequested.isPresent()) {
            RentableType rentableTypeRequested = optionalRentableTypeRequested.get();
            RentableTypeDTO responseValue = mapEntityToDTO(rentableTypeRequested);

            response = Optional.of(responseValue);
            rentableTypeRepository.deleteById(id);
            return response;
        }
        else throw new ResourceNotFoundException("Rentable Type Service: Could not delete Rentable Type with ID=" + id);
    }

    @Override
    public RentableTypeDTO mapEntityToDTO(RentableType rentableType) {
        RentableTypeDTO rentableTypeDTO = new RentableTypeDTO();

        rentableTypeDTO.setId(rentableType.getId());
        rentableTypeDTO.setName(rentableType.getName());
        return rentableTypeDTO;
    }

    @Override
    public RentableType mapDTOToEntity(RentableTypeDTO rentableTypeDTO){
        RentableType rentableType = new RentableType();

        rentableType.setId(rentableTypeDTO.getId());
        rentableType.setName(rentableTypeDTO.getName());
        return rentableType;
    }
}
