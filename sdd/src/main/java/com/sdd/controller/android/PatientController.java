package com.sdd.controller.android;


import com.sdd.request.PatientCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.PatientCreateResponse;
import com.sdd.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping("/create")
    public ResponseEntity<ApiResponse<PatientCreateResponse>> createPatient(@RequestBody PatientCreateRequest patientCreateRequest){
       ApiResponse<PatientCreateResponse> patientCreateResponseApiResponse = patientService.createPatient(patientCreateRequest);
       return new ResponseEntity<>(patientCreateResponseApiResponse, HttpStatus.OK);
    }
}
