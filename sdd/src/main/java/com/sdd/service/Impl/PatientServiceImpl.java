package com.sdd.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.*;
import com.sdd.entities.repository.*;
import com.sdd.exception.SDDException;
import com.sdd.request.PatientCreateRequest;
import com.sdd.request.VisitCreateRequest;
import com.sdd.request.VisitRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.PatientCreateResponse;
import com.sdd.response.VisitResponse;
import com.sdd.service.PatientService;
import com.sdd.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

     @Autowired
     private PatientRepository patientRepository;

     @Autowired
     private VisitRepository visitRepository;


     @Autowired
     private ImmunizationRepository immunizationRepository;


    @Autowired
    private InfantJaundiceTestRepository infantJaundiceTestRepository;


    @Autowired
    private InfantDiarrheaTestRepository infantDiarrheaTestRepository;



    @Autowired
    private FeedingAssessmentRepository feedingAssessmentRepository;


    @Autowired
    private RoutineAssessmentRepository routineAssessmentRepository;


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

    @Override
    @Transactional
    public ApiResponse<VisitResponse> createVisit(VisitCreateRequest visitCreateRequest) {

        final Immunization immunization = new Immunization();
        final FeedingAssessment feedingAssessment = new FeedingAssessment();
        final InfantDiarrheaTest infantDiarrheaTest = new InfantDiarrheaTest();
        final InfantJaundiceTest infantJaundiceTest = new InfantJaundiceTest();
        final RoutineAssessment routineAssessment = new RoutineAssessment();
        final Visit visit = new Visit();
        if(ObjectUtils.isEmpty(visitCreateRequest.getPatientVisit()) || ObjectUtils.isEmpty(visitCreateRequest.getPatientVisit().getPatientId())){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "invalid visit or patient id");
        }
        BeanUtils.copyProperties(visitCreateRequest.getPatientVisit(),visit);
        visit.setPatientType(visitCreateRequest.getPatientType());
        log.info("[createVisit] creating visit {}",visitCreateRequest);

        Visit visitOutPut = null;
        try{
            boolean value = patientRepository.existsById(visit.getPatientId());
            if(!value){
                throw new SDDException(HttpStatus.NOT_FOUND.value(), "patient dose not exist");
            }
            visitOutPut = visitRepository.save(visit);


            if(!ObjectUtils.isEmpty(visitCreateRequest.getImmunizationRequest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getImmunizationRequest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null Immunization");

                }
                BeanUtils.copyProperties(visitCreateRequest.getImmunizationRequest(),immunization);
                immunization.setVisitNo(visitOutPut.getId());
                immunizationRepository.save(immunization);
            }

            if(!ObjectUtils.isEmpty(visitCreateRequest.getFeedingAssessmentRequest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getFeedingAssessmentRequest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null FeedingAssessment");

                }
                BeanUtils.copyProperties(visitCreateRequest.getFeedingAssessmentRequest(),feedingAssessment);
                feedingAssessment.setVisitNno(visitOutPut.getId());
                feedingAssessmentRepository.save(feedingAssessment);
            }

            if(!ObjectUtils.isEmpty(visitCreateRequest.getInfantDiarrheaTestRequest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getInfantDiarrheaTestRequest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null InfantDiarrheaTest");

                }
                BeanUtils.copyProperties(visitCreateRequest.getInfantDiarrheaTestRequest(),infantDiarrheaTest);
                infantDiarrheaTest.setVisitNo(visitOutPut.getId());
                infantDiarrheaTestRepository.save(infantDiarrheaTest);
            }


            if(!ObjectUtils.isEmpty(visitCreateRequest.getInfantJaundiceTestRequest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getInfantJaundiceTestRequest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null InfantJaundiceTest");

                }
                BeanUtils.copyProperties(visitCreateRequest.getInfantJaundiceTestRequest(),infantJaundiceTest);
                infantJaundiceTest.setVisitNo(visitOutPut.getId());
                infantJaundiceTestRepository.save(infantJaundiceTest);
            }


            if(!ObjectUtils.isEmpty(visitCreateRequest.getRoutineAssessmentRequest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getRoutineAssessmentRequest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null RoutineAssessment");

                }
                BeanUtils.copyProperties(visitCreateRequest.getRoutineAssessmentRequest(),routineAssessment);
                routineAssessment.setVisitNno(visitOutPut.getId());
                routineAssessmentRepository.save(routineAssessment);
            }


             }catch (Exception  exception){
            exception.printStackTrace();
            throw new SDDException(HttpStatus.INTERNAL_SERVER_ERROR.value(),"visit failed");
        }
        final  VisitResponse visitResponse = new VisitResponse();
        visitResponse.setStatus(1);
        visitResponse.setMessage("success");
        return ResponseUtils.createSuccessResponse(visitResponse, new TypeReference<VisitResponse>() {});
    }
}
