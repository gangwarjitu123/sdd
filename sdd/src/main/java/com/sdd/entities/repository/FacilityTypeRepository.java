package com.sdd.entities.repository;

import com.sdd.entities.HealthFacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface FacilityTypeRepository extends JpaRepository<HealthFacilityType,Integer> {

    List<HealthFacilityType> findAllByFacilityTypeIdIn(Collection<Integer> facilityIds);
}
