package com.example.bono_challenge.repository;

import com.example.bono_challenge.entity.CarbonFootprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonFootprintRepository extends JpaRepository<CarbonFootprint, Long> {
}
