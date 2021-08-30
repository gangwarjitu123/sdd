package com.sdd.controller;

import com.sdd.exception.SDDException;
import com.sdd.response.*;
import com.sdd.service.CommonDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@Slf4j
public class CommonDataController {

    @Autowired
    private CommonDataService commonDataService;

    @RequestMapping("/states")
    public ResponseEntity<ApiResponse<List<StateResponse>>> getAllState(){
        return new ResponseEntity<>(commonDataService.getAllState(), HttpStatus.OK);
    }

    @RequestMapping("/districts/{stateId}")
    public ResponseEntity<ApiResponse<DistrictResponse>> getAllDistrictByState(@PathVariable(value = "stateId") Integer stateId){
       return new ResponseEntity<>(commonDataService.getAllDistrictByState(stateId),HttpStatus.OK);
    }

    @RequestMapping("/blocks/{districtCode}/{stateCode}")
    public ResponseEntity<ApiResponse<List<BlockResponse>>> getAllBlockByDistrictAndState(@PathVariable(value = "districtCode") Integer districtCode, @PathVariable(value = "stateCode") Integer stateCode){
        if(districtCode==null || stateCode==null){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "stateCode or districtCode is null");
        }
        return new ResponseEntity<>(commonDataService.getBlockResponse(districtCode,stateCode),HttpStatus.OK);
    }

    @RequestMapping("/facilityTypes/{blockCode}/{districtCode}/{stateCode}")
    public ResponseEntity<ApiResponse<Set<HealthFacilityTypeResponse>>> getAllFacilityTypeResponse(@PathVariable(value = "blockCode") Integer blockCode,@PathVariable(value = "districtCode") Integer districtCode, @PathVariable(value = "stateCode") Integer stateCode){
        if(districtCode == null || stateCode==null || blockCode ==null){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "stateId or districtId or blockId  is null");
        }
        log.info("[getAllFacilityTypeResponse ] enter blockId, {} stateId ,{} ,districtId, {} ",blockCode,stateCode,districtCode);
        return new ResponseEntity<>(commonDataService.getFacilityTypes(blockCode,districtCode,stateCode),HttpStatus.OK);
    }

    @RequestMapping("/facilities/{blockCode}/{stateCode}/{facilityTypeId}")
    public ResponseEntity<ApiResponse<List<HealthFacilityResponse>>> getHealthFacilityResponse(@PathVariable(value = "blockCode") Integer blockCode, @PathVariable(value = "stateCode") Integer stateCode, @PathVariable(value = "facilityTypeId") Integer facilityTypeId){
        ApiResponse<List<HealthFacilityResponse>> listApiResponse =  commonDataService.getHealthFacility(blockCode, stateCode, facilityTypeId);
        return new ResponseEntity<>(listApiResponse,HttpStatus.OK);
    }

    @RequestMapping("/subFacilities/{facilityCode}")
    public ResponseEntity<ApiResponse<List<HealthSubFacilityResponse>>> getSubFacilities(@PathVariable(value = "facilityCode") Integer facilityCode){
        if(facilityCode==null){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"facilityCode is null ");
        }
        ApiResponse<List<HealthSubFacilityResponse>>  listApiResponse = commonDataService.getSubFacilities(facilityCode);
        return new ResponseEntity<>(listApiResponse,HttpStatus.OK);
    }

}
