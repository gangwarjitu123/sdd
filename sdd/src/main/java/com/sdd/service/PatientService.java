package com.sdd.service;

import com.sdd.request.PatientCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.PatientCreateResponse;

public interface PatientService {

    public ApiResponse<PatientCreateResponse> createPatient(PatientCreateRequest patientCreateRequest);
}
