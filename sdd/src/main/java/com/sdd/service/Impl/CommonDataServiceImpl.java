package com.sdd.service.Impl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.*;
import com.sdd.entities.repository.*;
import com.sdd.exception.SDDException;
import com.sdd.response.*;
import com.sdd.service.CommonDataService;
import com.sdd.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class CommonDataServiceImpl implements CommonDataService {


    private StateRepository stateRepository;

    private DistrictRepository districtRepository;

    private HealthSubFacilityRepository healthSubFacilityRepository;

    private BlockRepository blockRepository;

    private HealthFacilityRepository healthFacilityRepository;

    private FacilityTypeRepository facilityTypeRepository;


    @Override
    public ApiResponse<List<StateResponse>> getAllState() {
        List<State> stateList = stateRepository.findAll();
        List<StateResponse> stateResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(stateList)) {

            stateList.forEach(state -> {
                stateResponses.add(StateResponse.builder().
                        stateName(state.getStateName())
                        .stateId(state.getId()).build());
            });
        }
        return ResponseUtils.createSuccessResponse(stateResponses, new TypeReference<List<StateResponse>>() {
        });
    }


    @Transactional
    public ApiResponse<DistrictResponse> getAllDistrictByState(Integer stateId) {
        Optional<State> state = stateRepository.findById(stateId);
        if (!state.isPresent()) {
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "invalid state ID");
        }

        List<District> districts = districtRepository.findByStateId(stateId);
        List<DistrictResponse.DistrictData> districtResponses = new ArrayList<>();
        //
        if (!CollectionUtils.isEmpty(districts)) {
            districts.forEach(district -> {
                DistrictResponse.DistrictData districtData = DistrictResponse.DistrictData.builder().
                        districtId(district.getDistrictId())
                        .districtName(district.getDistrictName())
                        .districtCode(district.getDistrictCode())
                        .mddsCode(district.getMddsCode()).build();
                districtResponses.add(districtData);
            });
        }

        DistrictResponse districtResponse = DistrictResponse.builder()
                .districts(districtResponses)
                .stateId(stateId)
                .stateName(state.get().getStateName())
                .build();
        return ResponseUtils.createSuccessResponse(districtResponse, new TypeReference<DistrictResponse>() {
        });
    }



    @Override
    public ApiResponse<List<BlockResponse>> getBlockResponse(Integer districtId, Integer stateId) {
        List<Block> blocks = blockRepository.findAllByDistrictCodeAndStateId(districtId, stateId);

        List<BlockResponse> blockResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blocks)) {

            for (Block districtBlock : blocks) {
                BlockResponse blockResponse = new BlockResponse();
                blockResponse.setHealthBlockCode(districtId);
                blockResponse.setHealthBlockId(districtBlock.getHealthBlockId());
                blockResponse.setHealthBlockName(districtBlock.getHealthBlockName());
                blockResponse.setTalukaId(districtBlock.getTalukaId());
                blockResponse.setMddsCode(districtBlock.getMddsCode());
                blockResponse.setHealthBlockCode(districtBlock.getHealthBlockCode());
                blockResponse.setStateId(stateId);
                blockResponses.add(blockResponse);
              }
            }
        return ResponseUtils.createSuccessResponse(blockResponses, new TypeReference<List<BlockResponse>>() {});
    }

    @Override
    public ApiResponse<Set<HealthFacilityTypeResponse>> getFacilityTypes(Integer blockId,Integer districtId, Integer stateId) {
        List<HealthFacility> healthFacilities = healthFacilityRepository.findAllByDistrictCodeAndHealthBlockCodeAndStateId(districtId,blockId,stateId);
        if(CollectionUtils.isEmpty(healthFacilities)){
            throw  new SDDException(HttpStatus.NOT_FOUND.value(), "No facility found for the districtId "+ districtId + " and blockId "+blockId +" stateId "+ stateId);
        }
        Set<Integer> healthFacilitiesTypeIds = new HashSet<>();
        healthFacilities.forEach(healthFacility -> {
            healthFacilitiesTypeIds.add(healthFacility.getHealthFacilityTypeId());
        });
        log.info("facilities Type ids {}",healthFacilitiesTypeIds);
        List<HealthFacilityType> healthFacilityTypes = facilityTypeRepository.findAllByFacilityTypeIdIn(healthFacilitiesTypeIds);
        Set<HealthFacilityTypeResponse> healthFacilityTypeResponses = new HashSet<>();
        if(!CollectionUtils.isEmpty(healthFacilityTypes)){
            healthFacilityTypes.forEach(healthFacilityType  -> {
                HealthFacilityTypeResponse healthFacilityTypeResponse = new HealthFacilityTypeResponse();
                healthFacilityTypeResponse.setFacilityTypeId(healthFacilityType.getFacilityTypeId());
                healthFacilityTypeResponse.setFacilityTypeName(healthFacilityType.getFacilityTypeName());
                healthFacilityTypeResponses.add(healthFacilityTypeResponse);
            });
        }
        return ResponseUtils.createSuccessResponse(healthFacilityTypeResponses,new TypeReference<Set<HealthFacilityTypeResponse>>(){});
    }

    @Override
    public ApiResponse<List<HealthFacilityResponse>> getHealthFacility(Integer blockCode, Integer stateCode,Integer facilityTypeId){
        List<HealthFacility> healthFacilities = healthFacilityRepository.findAllByStateIdAndHealthBlockCodeAndHealthFacilityTypeId(stateCode,blockCode,facilityTypeId);
        List<HealthFacilityResponse> healthFacilityResponses = new ArrayList<>();
        if(!CollectionUtils.isEmpty(healthFacilities)){

            for (HealthFacility healthFacilityOb : healthFacilities) {
                HealthFacilityResponse healthFacilityResponse = new HealthFacilityResponse();
                healthFacilityResponse.setHealthFacilityName(healthFacilityOb.getHealthFacilityName());
                healthFacilityResponse.setHealthFacilityCode(healthFacilityOb.getHealthFacilityCode());
                healthFacilityResponse.setHealthBlockCode(healthFacilityOb.getHealthBlockCode());
                healthFacilityResponse.setTalukaId(healthFacilityOb.getTalukaId());
                healthFacilityResponse.setDistrictCode(healthFacilityOb.getDistrictCode());
                healthFacilityResponses.add(healthFacilityResponse);
            }
        }
        return ResponseUtils.createSuccessResponse(healthFacilityResponses, new TypeReference<List<HealthFacilityResponse>>(){});
    }

    @Override
    public ApiResponse<List<HealthSubFacilityResponse>> getSubFacilities(Integer facilityCode){
       Set<HealthSubFacility> healthSubFacilities = healthSubFacilityRepository.findAllByHealthFacilityCode(facilityCode);
       List<HealthSubFacilityResponse> healthSubFacilityResponseList = new ArrayList<>();
       if(!CollectionUtils.isEmpty(healthSubFacilities)){

           for (HealthSubFacility healthSubFacility : healthSubFacilities) {
               HealthSubFacilityResponse healthSubFacilityResponse = new HealthSubFacilityResponse();
               healthSubFacilityResponse.setHealthSubFacilityCode(healthSubFacility.getHealthFacilitySubCode());
               healthSubFacilityResponse.setHealthFacilityCenterName(healthSubFacility.getHealthSubCenterName());
               healthSubFacilityResponse.setHealthFacilityCode(healthSubFacility.getHealthFacilityCode());
               healthSubFacilityResponseList.add(healthSubFacilityResponse);
           }
       }
       return  ResponseUtils.createSuccessResponse(healthSubFacilityResponseList,new TypeReference<List<HealthSubFacilityResponse>>(){});
    }
}

