package com.sdd.entities.repository;

import com.sdd.entities.District;
import com.sdd.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    List<District> findByState(State state);
}
