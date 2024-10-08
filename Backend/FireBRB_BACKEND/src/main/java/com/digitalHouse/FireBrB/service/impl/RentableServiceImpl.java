package com.digitalHouse.FireBrB.service.impl;

import com.digitalHouse.FireBrB.dto.RentableDTO;
import com.digitalHouse.FireBrB.exception.ResourceNotFoundException;
import com.digitalHouse.FireBrB.model.Rentable;
import com.digitalHouse.FireBrB.model.RentableType;
import com.digitalHouse.FireBrB.repository.IRentableRepository;
import com.digitalHouse.FireBrB.repository.IRentableTypeRepository;
import com.digitalHouse.FireBrB.request.FilterRequest;
import com.digitalHouse.FireBrB.service.IRentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Optional<RentableDTO> findByName(String name) {
        Optional<Rentable> optionalRentableRequested = rentableRepository.findByName(name);
        Optional<RentableDTO> responseDTO = null;

        if (optionalRentableRequested.isPresent()) {
            // Mapping the rentableType found in the database back to the DTO
            Rentable rentableRequested = optionalRentableRequested.get();

            RentableDTO rentableDTORequested = mapEntityToDTO(rentableRequested);

            responseDTO = Optional.of(rentableDTORequested);
        }
        else responseDTO = Optional.empty();
        return responseDTO;
    }

    @Override
    public List<RentableDTO> filterSearch(FilterRequest filterRequest) {
        Integer limit = filterRequest.getLimit();
        List<Rentable> entitiesSelected;

        if (filterRequest.getRandom()) {
            entitiesSelected = rentableRepository.findRandomRentables(limit);
        } else {
            String sortBy = filterRequest.getSortBy() != null ? filterRequest.getSortBy() : "id";
            String sortDirection = filterRequest.getSortDirection() != null ? filterRequest.getSortDirection() : "asc";

            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
            Pageable pageable = PageRequest.of(0, limit, sort);

            entitiesSelected = rentableRepository.findAll(pageable).toList();
        }

            List<RentableDTO> response = new ArrayList<>();
            for (Rentable rentable : entitiesSelected) {
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
        rentableDTO.setName(rentable.getName());
        rentableDTO.setAddress(rentable.getAddress());
        rentableDTO.setCity(rentable.getCity());
        rentableDTO.setRegion(rentable.getRegion());
        rentableDTO.setCountry(rentable.getCountry());
        rentableDTO.setPricePerNightUSD(rentable.getPricePerNightUSD());
        rentableDTO.setAssociatedImgsUrl(rentable.getAssociatedImgsUrl());
        rentableDTO.setStarRating(rentable.getStarRating());
        rentableDTO.setRentableTypeId(rentable.getRentableType().getId());
        rentableDTO.setGoogleMapsUrl(rentable.getGoogleMapsUrl());

        return rentableDTO;
    }
    public Rentable mapDTOToEntity(RentableDTO rentableDTO) throws ResourceNotFoundException {
        Rentable rentable = new Rentable();
        Optional<RentableType> rentableTypeAssociated = rentableTypeRepository.findById(rentableDTO.getRentableTypeId());

        if (rentableTypeAssociated.isPresent()) {
            rentable.setId(rentableDTO.getId());
            rentable.setName(rentableDTO.getName());
            rentable.setAddress(rentableDTO.getAddress());
            rentable.setCity(rentableDTO.getCity());
            rentable.setRegion(rentableDTO.getRegion());
            rentable.setCountry(rentableDTO.getCountry());
            rentable.setPricePerNightUSD(rentableDTO.getPricePerNightUSD());
            rentable.setAssociatedImgsUrl(rentableDTO.getAssociatedImgsUrl());
            rentable.setStarRating(rentableDTO.getStarRating());
            rentable.setRentableType(rentableTypeAssociated.get());
            rentable.setGoogleMapsUrl(rentableDTO.getGoogleMapsUrl());
        } else throw new ResourceNotFoundException("Rentable Service: Rentable type with ID=" + rentableDTO.getRentableTypeId() + " not found");

        return rentable;
    }
}
