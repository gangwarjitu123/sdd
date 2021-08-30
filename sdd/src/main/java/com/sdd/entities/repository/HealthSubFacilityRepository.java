package com.sdd.entities.repository;

import com.sdd.entities.HealthSubFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HealthSubFacilityRepository extends JpaRepository<HealthSubFacility,Integer> {

    Set<HealthSubFacility> findAllByHealthFacilityCode(Integer healthFacilityCode);
    HealthSubFacility findByHealthFacilityCodeAndHealthFacilitySubCode(Integer healthFacilityCode,Integer healthSubFacilityCode);
}
