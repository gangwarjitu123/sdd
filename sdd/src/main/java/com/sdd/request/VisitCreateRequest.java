package com.sdd.request;

import lombok.Data;

@Data
public class VisitCreateRequest {

private VisitRequest patientVisit;
private String patientType;
private ImmunizationRequest immunization;
private InfantDiarrheaTestRequest infantDiarrheaTest;
private InfantJaundiceTestRequest infantJaundiceTest;
private RoutineAssessmentRequest routineAssessment;
private FeedingAssessmentRequest feedingAssessment;

}
