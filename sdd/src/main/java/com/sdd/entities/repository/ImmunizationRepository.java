package com.sdd.entities.repository;

import com.sdd.entities.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmunizationRepository extends JpaRepository<Immunization,Integer> {
}
