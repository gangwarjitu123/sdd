package com.sdd.entities.repository;

import com.sdd.entities.User;
import com.sdd.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {

    List<Visit> findAllByPatientId(int patientId);
}
