package com.digitalHouse.FireBrB.repository;

import com.digitalHouse.FireBrB.model.RentableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRentableTypeRepository extends JpaRepository<RentableType, Long> {
    @Query(value = "SELECT * FROM RENTABLE_TYPE WHERE NAME = ?1", nativeQuery = true)
    Optional<RentableType> findByName(String name);
}
