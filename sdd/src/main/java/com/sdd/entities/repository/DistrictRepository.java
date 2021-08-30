package com.sdd.entities.repository;

import com.sdd.entities.District;
import com.sdd.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    List<District> findByStateId(Integer stateId);
    Optional<District> findByDistrictCodeAndStateId(Integer districtCode, Integer stateId);
}
