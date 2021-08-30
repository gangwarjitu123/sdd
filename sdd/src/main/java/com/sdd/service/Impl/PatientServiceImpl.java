package com.sdd.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.Patient;
import com.sdd.entities.repository.PatientRepository;
import com.sdd.exception.SDDException;
import com.sdd.request.PatientCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.PatientCreateResponse;
import com.sdd.service.PatientService;
import com.sdd.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

     @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ApiResponse<PatientCreateResponse> createPatient(PatientCreateRequest patientCreateRequest) {
       final Patient patient = new Patient();
       Patient outPutUser = null;
        BeanUtils.copyProperties(patientCreateRequest,patient);
        try{
            outPutUser = patientRepository.save(patient);
            log.info("[createPatient] patient created with following details {}",outPutUser.getPatientId());
        }catch (Exception exception){
            throw new SDDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "patient creation failed");
        }
        PatientCreateResponse patientCreateResponse = new PatientCreateResponse();
        patientCreateResponse.setPatientId(outPutUser.getPatientId());
        patientCreateResponse.setMessage("success");
        patientCreateResponse.setStatus(1);

        return ResponseUtils.createSuccessResponse(patientCreateResponse, new TypeReference<PatientCreateResponse>() {});
    }
}
