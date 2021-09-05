package com.sdd.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.*;
import com.sdd.entities.repository.*;
import com.sdd.exception.SDDException;
import com.sdd.request.PatientCreateRequest;
import com.sdd.request.VisitCreateRequest;
import com.sdd.response.*;
import com.sdd.service.PatientService;
import com.sdd.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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



    @Autowired
    private UserRepository userRepository;



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
    public ApiResponse<List<Patient>> getAllPatient() {
        List<Patient> patientList =  patientRepository.findAll();

        return ResponseUtils.createSuccessResponse(patientList, new TypeReference<List<Patient>>() {
        });
    }

    @Override
    public ApiResponse<List<VisitExcelResponse>> getAllVisit() {
        List<VisitExcelResponse> allVisitData = new ArrayList<>();
        List<Visit> visitList = visitRepository.findAll();

        if (visitList.isEmpty()) {
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "No Data found.");
        }

        if (!CollectionUtils.isEmpty(visitList)) {
            visitList.forEach(visitData -> {
                VisitExcelResponse visitAddData = new VisitExcelResponse();
                visitAddData.setId(visitData.getId());
                visitAddData.setVisitNo(visitData.getVisitNo());
                visitAddData.setAgeInDays(visitData.getAgeInDays());
                visitAddData.setVisitNo(visitData.getVisitNo());
                visitAddData.setVisitDate(visitData.getVisitDate());
                visitAddData.setPatientId(visitData.getPatientId());
                visitAddData.setAgeInYears(visitData.getAgeInYears());
                visitAddData.setPatientType(visitData.getPatientType());
                visitAddData.setAgeInWeeks(visitData.getAgeInWeeks());
                visitAddData.setCurrentStatus(visitData.getCurrentStatus());
                visitAddData.setGaWeeks(visitData.getGaWeeks());
                visitAddData.setFollowUpDate(visitData.getFollowUpDate());
                visitAddData.setDangerSign(visitData.getDangerSign());
                visitAddData.setTemperature(visitData.getTemperature());
                visitAddData.setTimeStamp(visitData.getTimeStamp());
                visitAddData.setSymptoms(visitData.getSymptoms());
                visitAddData.setReferred(visitData.getReferred());
                visitAddData.setReferredLocation(visitData.getReferredLocation());
                visitAddData.setMobileId(visitData.getMobileId());


                Immunization immunizations = immunizationRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(immunizations)){
                    visitAddData.setIsVerified(immunizations.getIsVerified());
                    visitAddData.setIsCompleted(immunizations.getIsCompleted());
                    visitAddData.setSelectedVaccines(immunizations.getSelectedVaccines());
                    visitAddData.setSNo(immunizations.getSNo());
                }


                InfantJaundiceTest infantJaundiceTest = infantJaundiceTestRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());

                if(!ObjectUtils.isEmpty(infantJaundiceTest)) {
                    visitAddData.setYellowPalmSole(infantJaundiceTest.getYellowPalmSole());
                    visitAddData.setJaudiceAge(infantJaundiceTest.getJaudiceAge());
                }


                InfantDiarrheaTest infantDiarrheaTest = infantDiarrheaTestRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(infantDiarrheaTest)) {
                    visitAddData.setSunkenEyes(infantDiarrheaTest.getSunkenEyes());
                    visitAddData.setSkinPinchVerySlow(infantDiarrheaTest.getSkinPinchVerySlow());
                    visitAddData.setSkinPinchSlow(infantDiarrheaTest.getSkinPinchSlow());
                    visitAddData.setRestLess(infantDiarrheaTest.getRestLess());
                    visitAddData.setNoMovement(infantDiarrheaTest.getNoMovement());
                }

                FeedingAssessment feedingAssessment = feedingAssessmentRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(feedingAssessment)) {
                    visitAddData.setAttachmentToBreastNoGood(feedingAssessment.getAttachmentToBreastNoGood());
                    visitAddData.setBreastFeedDayCount(feedingAssessment.getBreastFeedDayCount());
                    visitAddData.setBreastNippleProblem(feedingAssessment.getBreastNippleProblem());
                    visitAddData.setUlcerWhitePatch(feedingAssessment.getUlcerWhitePatch());
                    visitAddData.setNotSuckingEffectively(feedingAssessment.getNotSuckingEffectively());
                }


                RoutineAssessment routineAssessment = routineAssessmentRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());


                if(!ObjectUtils.isEmpty(routineAssessment)) {
                    visitAddData.setBreathAMinute(routineAssessment.getBreathAMinute());
                    visitAddData.setHadConvulsions(routineAssessment.getHadConvulsions());
                    visitAddData.setMovementOnSimulation(routineAssessment.getMovementOnSimulation());
                    visitAddData.setNotFeedingWell(routineAssessment.getNotFeedingWell());
                    visitAddData.setSevereChestIndrawing(routineAssessment.getSevereChestIndrawing());
                    visitAddData.setSkinPustules(routineAssessment.getSkinPustules());
                    visitAddData.setUmbilicusIndrawingPus(routineAssessment.getUmbilicusIndrawingPus());
                    visitAddData.setUmbilicusRed(routineAssessment.getUmbilicusRed());

                }


                allVisitData.add(visitAddData);
            });
        }

        return ResponseUtils.createSuccessResponse(allVisitData, new TypeReference<List<VisitExcelResponse>>() {
        });
    }






    @Override
    public ApiResponse<List<Patient>> getAllPatient(Integer cho) {
        List<Patient> patientList = new ArrayList<Patient>();

        Optional<User> userData = userRepository.findById(cho);

        if(userData.get() != null){
            if(userData.get().getLevel() ==1){
                patientList = patientRepository.findByCreatedBy(cho);
            }else{
                throw new SDDException(HttpStatus.UNAUTHORIZED.value(), " CHO Id not exist.");
            }
        }else{
            throw new SDDException(HttpStatus.UNAUTHORIZED.value(), "Invalid CHO Id");
        }



        return ResponseUtils.createSuccessResponse(patientList, new TypeReference<List<Patient>>() {
        });
    }

    @Override
    public ApiResponse<List<VisitExcelResponse>> getAllVisit(Integer cho) {
        List<VisitExcelResponse> allVisitData = new ArrayList<>();
        List<Visit> visitList = new ArrayList<Visit>();
        List<Patient> patientList = new ArrayList<Patient>();


        Optional<User> userData = userRepository.findById(cho);

        if(userData.get() != null){
            if(userData.get().getLevel() == 1){
                patientList = patientRepository.findByCreatedBy(cho);
            }else{
                throw new SDDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), " CHO Id not exist.");
            }
        }else{
            throw new SDDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Invalid CHO Id");
        }

        if (!CollectionUtils.isEmpty(patientList)) {
            patientList.forEach(ptientData -> {
                visitList.addAll(visitRepository.findAllByPatientId(ptientData.getPatientId()));

            });
        }


        if (visitList.isEmpty()) {
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "No Data found.");
        }

        if (!CollectionUtils.isEmpty(visitList)) {
            visitList.forEach(visitData -> {
                VisitExcelResponse visitAddData = new VisitExcelResponse();
                visitAddData.setId(visitData.getId());
                visitAddData.setVisitNo(visitData.getVisitNo());
                visitAddData.setAgeInDays(visitData.getAgeInDays());
                visitAddData.setVisitNo(visitData.getVisitNo());
                visitAddData.setVisitDate(visitData.getVisitDate());
                visitAddData.setPatientId(visitData.getPatientId());
                visitAddData.setAgeInYears(visitData.getAgeInYears());
                visitAddData.setPatientType(visitData.getPatientType());
                visitAddData.setAgeInWeeks(visitData.getAgeInWeeks());
                visitAddData.setCurrentStatus(visitData.getCurrentStatus());
                visitAddData.setGaWeeks(visitData.getGaWeeks());
                visitAddData.setFollowUpDate(visitData.getFollowUpDate());
                visitAddData.setDangerSign(visitData.getDangerSign());
                visitAddData.setTemperature(visitData.getTemperature());
                visitAddData.setTimeStamp(visitData.getTimeStamp());
                visitAddData.setSymptoms(visitData.getSymptoms());
                visitAddData.setReferred(visitData.getReferred());
                visitAddData.setReferredLocation(visitData.getReferredLocation());
                visitAddData.setMobileId(visitData.getMobileId());


                Immunization immunizations = immunizationRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(immunizations)){
                    visitAddData.setIsVerified(immunizations.getIsVerified());
                    visitAddData.setIsCompleted(immunizations.getIsCompleted());
                    visitAddData.setSelectedVaccines(immunizations.getSelectedVaccines());
                    visitAddData.setSNo(immunizations.getSNo());
                }


                InfantJaundiceTest infantJaundiceTest = infantJaundiceTestRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());

                if(!ObjectUtils.isEmpty(infantJaundiceTest)) {
                    visitAddData.setYellowPalmSole(infantJaundiceTest.getYellowPalmSole());
                    visitAddData.setJaudiceAge(infantJaundiceTest.getJaudiceAge());
                }


                InfantDiarrheaTest infantDiarrheaTest = infantDiarrheaTestRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(infantDiarrheaTest)) {
                    visitAddData.setSunkenEyes(infantDiarrheaTest.getSunkenEyes());
                    visitAddData.setSkinPinchVerySlow(infantDiarrheaTest.getSkinPinchVerySlow());
                    visitAddData.setSkinPinchSlow(infantDiarrheaTest.getSkinPinchSlow());
                    visitAddData.setRestLess(infantDiarrheaTest.getRestLess());
                    visitAddData.setNoMovement(infantDiarrheaTest.getNoMovement());
                }

                FeedingAssessment feedingAssessment = feedingAssessmentRepository.findByPatientIdAndVisitNo(visitData.getPatientId(),visitData.getVisitNo());
                if(!ObjectUtils.isEmpty(feedingAssessment)) {
                    visitAddData.setAttachmentToBreastNoGood(feedingAssessment.getAttachmentToBreastNoGood());
                    visitAddData.setBreastFeedDayCount(feedingAssessment.getBreastFeedDayCount());
                    visitAddData.setBreastNippleProblem(feedingAssessment.getBreastNippleProblem());
                    visitAddData.setUlcerWhitePatch(feedingAssessment.getUlcerWhitePatch());
                    visitAddData.setNotSuckingEffectively(feedingAssessment.getNotSuckingEffectively());
                }
                allVisitData.add(visitAddData);
            });
        }

        return ResponseUtils.createSuccessResponse(allVisitData, new TypeReference<List<VisitExcelResponse>>() {
        });
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
            boolean value = patientRepository.existsByPatientId(visit.getPatientId());
            if(!value){
                throw new SDDException(HttpStatus.NOT_FOUND.value(), "patient dose not exist");
            }
            visitOutPut = visitRepository.save(visit);


            if(!ObjectUtils.isEmpty(visitCreateRequest.getImmunization())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getImmunization().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null Immunization");

                }
                BeanUtils.copyProperties(visitCreateRequest.getImmunization(),immunization);
                immunization.setVisitNo(visitOutPut.getVisitNo());
                immunization.setPatientId(visit.getPatientId());
                immunizationRepository.save(immunization);
            }

            if(!ObjectUtils.isEmpty(visitCreateRequest.getFeedingAssessment())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getFeedingAssessment().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null FeedingAssessment");

                }
                BeanUtils.copyProperties(visitCreateRequest.getFeedingAssessment(),feedingAssessment);
                feedingAssessment.setVisitNo(visitOutPut.getVisitNo());
                feedingAssessment.setPatientId(visit.getPatientId());
                feedingAssessmentRepository.save(feedingAssessment);
            }

            if(!ObjectUtils.isEmpty(visitCreateRequest.getInfantDiarrheaTest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getInfantDiarrheaTest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null InfantDiarrheaTest");

                }
                BeanUtils.copyProperties(visitCreateRequest.getInfantDiarrheaTest(),infantDiarrheaTest);
                infantDiarrheaTest.setVisitNo(visitOutPut.getId());
                infantDiarrheaTest.setPatientId(visit.getPatientId());
                infantDiarrheaTestRepository.save(infantDiarrheaTest);
            }


            if(!ObjectUtils.isEmpty(visitCreateRequest.getInfantJaundiceTest())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getInfantJaundiceTest().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null InfantJaundiceTest");

                }
                BeanUtils.copyProperties(visitCreateRequest.getInfantJaundiceTest(),infantJaundiceTest);
                infantJaundiceTest.setVisitNo(visitOutPut.getVisitNo());
                infantDiarrheaTest.setPatientId(visit.getPatientId());
                infantJaundiceTestRepository.save(infantJaundiceTest);
            }


            if(!ObjectUtils.isEmpty(visitCreateRequest.getRoutineAssessment())){
                if(ObjectUtils.isEmpty(visitCreateRequest.getRoutineAssessment().getPatientId())){
                    throw new SDDException(HttpStatus.BAD_REQUEST.value(), "patient id is null RoutineAssessment");

                }
                BeanUtils.copyProperties(visitCreateRequest.getRoutineAssessment(),routineAssessment);
                routineAssessment.setVisitNo(visitOutPut.getVisitNo());
                routineAssessment.setPatientId(visit.getPatientId());
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
