package com.sdd.controller.android;


import com.sdd.entities.Patient;
import com.sdd.entities.Visit;
import com.sdd.request.PatientCreateRequest;
import com.sdd.request.VisitCreateRequest;
import com.sdd.request.VisitRequest;
import com.sdd.response.*;
import com.sdd.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/visit")
    public ResponseEntity<ApiResponse<VisitResponse>> createVisit(@RequestBody VisitCreateRequest visitCreateRequest){
       ApiResponse<VisitResponse> visitResponseApiResponse = patientService.createVisit(visitCreateRequest);
       return new ResponseEntity<>(visitResponseApiResponse,HttpStatus.OK);
    }



    @RequestMapping("/getAllPatient")
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatient(){
        return new ResponseEntity<>(patientService.getAllPatient(), HttpStatus.OK);
    }


    @RequestMapping("/getAllVisit")
    public ResponseEntity<ApiResponse<List<VisitExcelResponse>>> getAllVisit(){
        return new ResponseEntity<>(patientService.getAllVisit(), HttpStatus.OK);
    }



    @RequestMapping("/getAllPatientByChoID/{choID}")
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatientByChoID(@PathVariable(value = "choID") Integer choID){
        return new ResponseEntity<>(patientService.getAllPatient(choID), HttpStatus.OK);
    }


    @RequestMapping("/getAllVisitByChoID/{choID}")
    public ResponseEntity<ApiResponse<List<VisitExcelResponse>>> getAllVisitByChoID(@PathVariable(value = "choID") Integer choID){
        return new ResponseEntity<>(patientService.getAllVisit(choID), HttpStatus.OK);
    }


}
