package com.sdd.entities.repository;

import com.sdd.entities.District;
import com.sdd.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    public boolean existsByPatientId(Integer patientId);
    List<Patient> findByCreatedBy(Integer createdBy);
}
