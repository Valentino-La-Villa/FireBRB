package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.dto.RentableTypeDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.model.Reservation;
import com.digitalHouse.FireBrB.repository.IRentableRepository;
import com.digitalHouse.FireBrB.repository.IRentableTypeRepository;
import com.digitalHouse.FireBrB.service.IRentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentableServiceImpl implements IRentableService {

    private IRentableRepository rentableRepository;
    private IRentableTypeRepository rentableTypeRepository;

    @Autowired
    public RentableServiceImpl(IRentableRepository rentableRepository, IRentableTypeRepository rentableTypeRepository) {
        this.rentableRepository = rentableRepository;
        this.rentableTypeRepository = rentableTypeRepository;
    }

    @Override
    public RentableDTO save(RentableDTO rentableDTO) throws ResourceNotFoundException {
        Rentable rentable = mapDTOToEntity(rentableDTO);
        Rentable actualRentable = rentableRepository.save(rentable);

        // Returning a DTO with the saved data

        RentableDTO responseDTO = mapEntityToDTO(actualRentable);

        return responseDTO;
    }

    @Override
    public Optional<RentableDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Rentable> optionalRentableRequested = rentableRepository.findById(id);
        Optional<RentableDTO> responseDTO = null;

        if (optionalRentableRequested.isPresent()) {
            // Mapping the rentableType found in the database back to the DTO
            Rentable rentableRequested = optionalRentableRequested.get();

            RentableDTO rentableDTORequested = mapEntityToDTO(rentableRequested);

            responseDTO = Optional.of(rentableDTORequested);
        }
        else throw new ResourceNotFoundException("Rentable Service: Could not find Rentable with ID=" + id);
        return responseDTO;
    }

    @Override
    public List<RentableDTO> findAll() {
        List<Rentable> rentables = rentableRepository.findAll();
        List<RentableDTO> response = new ArrayList<>();

        for (Rentable rentable : rentables) {
            RentableDTO rentableDTO = mapEntityToDTO(rentable);
            response.add(rentableDTO);
        }
        return response;
    }

    @Override
    public RentableDTO update(RentableDTO rentableDTO) throws ResourceNotFoundException {
        Optional<Rentable> optionalRentableRequested = rentableRepository.findById(rentableDTO.getId());

        if (optionalRentableRequested.isPresent()) {
            Rentable rentableRequested = optionalRentableRequested.get();
            rentableRequested = mapDTOToEntity(rentableDTO);

            rentableRepository.save(rentableRequested);

            RentableDTO response = mapEntityToDTO(rentableRequested);

            return response;
        }
        else throw new ResourceNotFoundException("Rentable Service: Could not update Rentable with ID=" + rentableDTO.getId());
    }

    @Override
    public Optional<RentableDTO> delete(Long id) throws ResourceNotFoundException {
        Optional<Rentable> optionalRentableRequested = rentableRepository.findById(id);
        Optional<RentableDTO> response;

        if (optionalRentableRequested.isPresent()) {
            Rentable rentableRequested = optionalRentableRequested.get();
            RentableDTO responseValue = mapEntityToDTO(rentableRequested);

            response = Optional.of(responseValue);
            rentableRepository.deleteById(id);
            return response;
        }
        else throw new ResourceNotFoundException("Rentable Service: Could not delete Rentable with ID=" + id);
    }

    public RentableDTO mapEntityToDTO(Rentable rentable) {
        RentableDTO rentableDTO = new RentableDTO();

        rentableDTO.setId(rentable.getId());
        rentableDTO.setAddress(rentable.getAddress());
        rentableDTO.setPostalCode(rentable.getPostalCode());
        rentableDTO.setPricePerNightUSD(rentable.getPricePerNightUSD());
        rentableDTO.setAssociatedImgsUrl(rentable.getAssociatedImgsUrl());
        rentableDTO.setRentableTypeId(rentable.getRentableType().getId());

        return rentableDTO;
    }
    public Rentable mapDTOToEntity(RentableDTO rentableDTO) throws ResourceNotFoundException {
        Rentable rentable = new Rentable();
        Optional<RentableType> rentableTypeAssociated = rentableTypeRepository.findById(rentableDTO.getRentableTypeId());

        if (rentableTypeAssociated.isPresent()) {
            rentable.setId(rentableDTO.getId());
            rentable.setAddress(rentableDTO.getAddress());
            rentable.setPostalCode(rentableDTO.getPostalCode());
            rentable.setPricePerNightUSD(rentableDTO.getPricePerNightUSD());
            rentable.setAssociatedImgsUrl(rentableDTO.getAssociatedImgsUrl());
            rentable.setRentableType(rentableTypeAssociated.get());
        } else throw new ResourceNotFoundException("Rentable Service: Rentable type with ID=" + rentableDTO.getRentableTypeId() + " not found");

        return rentable;
    }
}
