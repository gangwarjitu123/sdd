package com.sdd.entities.repository;

import com.sdd.entities.FeedingAssessment;
import com.sdd.entities.Immunization;
import com.sdd.entities.InfantDiarrheaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedingAssessmentRepository extends JpaRepository<FeedingAssessment,Integer> {

    FeedingAssessment findByPatientIdAndVisitNo(Integer patientId, Integer visitNo);

}
