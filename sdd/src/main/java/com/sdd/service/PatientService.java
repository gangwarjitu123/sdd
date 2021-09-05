package com.sdd.service;

import com.sdd.entities.Patient;
import com.sdd.entities.Visit;
import com.sdd.request.PatientCreateRequest;
import com.sdd.request.VisitCreateRequest;
import com.sdd.request.VisitRequest;
import com.sdd.response.*;

import java.util.List;

public interface PatientService {

    public ApiResponse<PatientCreateResponse> createPatient(PatientCreateRequest patientCreateRequest);
    ApiResponse<List<Patient>> getAllPatient();
    ApiResponse<List<VisitExcelResponse>> getAllVisit();
    ApiResponse<List<Patient>> getAllPatient(Integer cho);
    ApiResponse<List<VisitExcelResponse>> getAllVisit(Integer cho);
    ApiResponse<VisitResponse> createVisit(VisitCreateRequest visitCreateRequest);
}
