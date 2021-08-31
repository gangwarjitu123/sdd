package com.sdd.service;

import com.sdd.request.PatientCreateRequest;
import com.sdd.request.VisitCreateRequest;
import com.sdd.request.VisitRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.PatientCreateResponse;
import com.sdd.response.VisitResponse;

public interface PatientService {

    public ApiResponse<PatientCreateResponse> createPatient(PatientCreateRequest patientCreateRequest);

    ApiResponse<VisitResponse> createVisit(VisitCreateRequest visitCreateRequest);
}
