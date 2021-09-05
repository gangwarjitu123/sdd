package com.sdd.entities.repository;

import com.sdd.entities.FeedingAssessment;
import com.sdd.entities.InfantJaundiceTest;
import com.sdd.entities.RoutineAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineAssessmentRepository extends JpaRepository<RoutineAssessment,Integer> {

    RoutineAssessment findByPatientIdAndVisitNo(Integer patientId, Integer visitNo);
}
