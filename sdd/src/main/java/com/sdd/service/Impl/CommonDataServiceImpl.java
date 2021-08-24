package com.sdd.service.Impl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.Block;
import com.sdd.entities.District;
import com.sdd.entities.HealthFacilityType;
import com.sdd.entities.State;
import com.sdd.entities.repository.DistrictRepository;
import com.sdd.entities.repository.StateRepository;
import com.sdd.exception.SDDException;
import com.sdd.response.*;
import com.sdd.service.CommonDataService;
import com.sdd.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CommonDataServiceImpl implements CommonDataService {


    private StateRepository stateRepository;

    private DistrictRepository districtRepository;

    @Override
    public ApiResponse<List<StateResponse>> getAllState() {

        List<State>  stateList = stateRepository.findAll();
        List<StateResponse>  stateResponses = new ArrayList<>();
        if(!CollectionUtils.isEmpty(stateList)){

            stateList.forEach(state -> {
                stateResponses.add(StateResponse.builder().
                        stateName(state.getStateName())
                        .stateId(state.getId()).build());
            });
        }
        return ResponseUtils.createSuccessResponse(stateResponses, new TypeReference<List<StateResponse>>(){});
    }


    @Transactional
    public ApiResponse<DistrictResponse> getAllDistrictByState(Integer stateId){
       Optional<State> state = stateRepository.findById(stateId);
       if(!state.isPresent()){
          throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid state ID");
       }
        List<District> districts = districtRepository.findByState(state.get());
        List<DistrictResponse.DistrictData>  districtResponses = new ArrayList<>();

       if(!CollectionUtils.isEmpty(districts)){
           districts.forEach(district -> {
               DistrictResponse.DistrictData districtData = DistrictResponse.DistrictData.builder().
                       districtId(district.getDistrictId())
                       .districtName(district.getDistrictName())
                       .districtCode(district.getDistrictCode())
                       .mddsCode(district.getMddsCode()).build();
               if(!CollectionUtils.isEmpty(district.getHealthBlock())){
                  Set<Block> districtBlocks  = district.getHealthBlock();
                  List<BlockResponse> blockResponses = new ArrayList<>();
                   for (Block districtBlock : districtBlocks) {
                        BlockResponse blockResponse = new BlockResponse();
                        blockResponse.setDistrictId(district.getDistrictId());
                        blockResponse.setHealthBlockId(districtBlock.getHealthBlockId());
                        blockResponse.setHealthBlockName(districtBlock.getHealthBlockName());
                        blockResponse.setTalukaId(districtBlock.getTalukaId());
                        blockResponse.setMddsCode(districtBlock.getMddsCode());
                        blockResponse.setHealthBlockCode(districtBlock.getHealthBlockCode());
                       blockResponses.add(blockResponse);
                   }
                   districtData.setBlockData(blockResponses);
               }

               if(!CollectionUtils.isEmpty(district.getHealthFacility())){
                   List<HealthFacilityResponse> healthFacilityResponses = new ArrayList<>();

                   district.getHealthFacility().forEach(healthFacility -> {
                       HealthFacilityResponse healthFacilityResponse = new HealthFacilityResponse();
                       healthFacilityResponse.setHealthFacilityId(healthFacility.getHealthFacilityId());
                       healthFacilityResponse.setHealthFacilityCode(healthFacility.getHealthFacilityCode());
                       healthFacilityResponse.setDistrictId(district.getDistrictId());
                       healthFacilityResponse.setHealthBlockId(healthFacility.getHealthBlockId());
                       healthFacilityResponse.setTalukaId(healthFacility.getTalukaId());

                       //get type data
                       HealthFacilityType healthFacilityType = healthFacility.getHealthFacilityType();
                       HealthFacilityTypeResponse facilityTypeResponse = new HealthFacilityTypeResponse();
                       facilityTypeResponse.setFacilityTypeId(healthFacilityType.getFacilityTypeId());
                       facilityTypeResponse.setFacilityTypeName(healthFacilityType.getFacilityTypeName());

                       healthFacilityResponse.setHealthFacilityTypeData(facilityTypeResponse);
                       healthFacilityResponses.add(healthFacilityResponse);
                   });
                   districtData.setFacilityData(healthFacilityResponses);
               }
               districtResponses.add(districtData);
           });
       }
       DistrictResponse districtResponse = DistrictResponse.builder()
               .districts(districtResponses)
               .stateId(stateId)
               .stateName(state.get().getStateName())
               .build();
       return ResponseUtils.createSuccessResponse(districtResponse, new TypeReference<DistrictResponse>() {});
    }
}
