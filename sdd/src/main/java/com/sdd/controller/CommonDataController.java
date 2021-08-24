package com.sdd.controller;

import com.sdd.response.ApiResponse;
import com.sdd.response.DistrictResponse;
import com.sdd.response.StateResponse;
import com.sdd.service.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonDataController {

    @Autowired
    private CommonDataService commonDataService;

    @RequestMapping("/states")
    public ResponseEntity<ApiResponse<List<StateResponse>>> getAllState(){

        return new ResponseEntity<>(commonDataService.getAllState(), HttpStatus.OK);
    }

    @RequestMapping("/district/{stateId}")
    public ResponseEntity<ApiResponse<DistrictResponse>> getAllDistrictByState(@PathVariable(value = "stateId") Integer stateId){
       return new ResponseEntity<>(commonDataService.getAllDistrictByState(stateId),HttpStatus.OK);
    }
}
