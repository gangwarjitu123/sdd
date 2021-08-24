package com.sdd.service;

import com.sdd.response.ApiResponse;
import com.sdd.response.DistrictResponse;
import com.sdd.response.StateResponse;

import java.util.List;

public interface CommonDataService {
    ApiResponse<List<StateResponse>> getAllState();
    ApiResponse<DistrictResponse> getAllDistrictByState(Integer stateId);
}
