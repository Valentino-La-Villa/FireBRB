package com.digitalHouse.FireBrB.repository;

import com.digitalHouse.FireBrB.model.RentableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentableTypeRepository extends JpaRepository<RentableType, Long> {
}
