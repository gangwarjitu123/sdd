package com.sdd.request;

import lombok.Data;

@Data
public class VisitCreateRequest {

private VisitRequest patientVisit;
private String patientType;
private ImmunizationRequest immunizationRequest;
private InfantDiarrheaTestRequest infantDiarrheaTestRequest;
private InfantJaundiceTestRequest infantJaundiceTestRequest;
private RoutineAssessmentRequest routineAssessmentRequest;
private FeedingAssessmentRequest feedingAssessmentRequest;

}
