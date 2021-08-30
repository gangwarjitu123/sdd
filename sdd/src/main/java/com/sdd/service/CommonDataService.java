package com.sdd.service;

import com.sdd.response.*;

import java.util.List;
import java.util.Set;

public interface CommonDataService {
    ApiResponse<List<StateResponse>> getAllState();
    ApiResponse<DistrictResponse> getAllDistrictByState(Integer stateId);
    ApiResponse<List<BlockResponse>> getBlockResponse(Integer districtId,Integer stateId);
    ApiResponse<Set<HealthFacilityTypeResponse>> getFacilityTypes(Integer districtId,Integer blockId, Integer stateId);
    ApiResponse<List<HealthFacilityResponse>> getHealthFacility(Integer blockCode, Integer stateCode,Integer facilityTypeId);
    public ApiResponse<List<HealthSubFacilityResponse>> getSubFacilities(Integer facilityCode);
}
