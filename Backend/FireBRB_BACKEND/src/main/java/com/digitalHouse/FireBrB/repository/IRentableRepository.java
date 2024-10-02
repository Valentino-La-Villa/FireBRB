package com.digitalHouse.FireBrB.repository;

import com.digitalHouse.FireBrB.model.Rentable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRentableRepository extends JpaRepository<Rentable, Long> {

    @Query(value = "SELECT * FROM RENTABLES ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Rentable> findRandomRentables(Integer limit);

    @Query(value = "SELECT * FROM RENTABLES WHERE NAME = ?1", nativeQuery = true)
    Optional<Rentable> findByName(String name);
}
