package com.digitalHouse.FireBrB.repository;

import com.digitalHouse.FireBrB.model.Rentable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentableRepository extends JpaRepository<Rentable, Long> {
}
