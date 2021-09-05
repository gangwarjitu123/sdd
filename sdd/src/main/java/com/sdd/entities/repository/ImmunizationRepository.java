package com.sdd.entities.repository;

import com.sdd.entities.District;
import com.sdd.entities.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImmunizationRepository extends JpaRepository<Immunization,Integer> {

    Immunization findByPatientIdAndVisitNo(Integer patientId, Integer visitNo);

}
