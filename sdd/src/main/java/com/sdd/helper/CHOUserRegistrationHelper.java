package com.sdd.helper;

import com.sdd.entities.Block;
import com.sdd.entities.HealthFacility;
import com.sdd.entities.HealthSubFacility;
import com.sdd.entities.User;
import com.sdd.entities.repository.BlockRepository;
import com.sdd.entities.repository.HealthFacilityRepository;
import com.sdd.entities.repository.HealthSubFacilityRepository;
import com.sdd.exception.SDDException;
import com.sdd.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;



@Service("CHO")
public class CHOUserRegistrationHelper extends AbstractUserRegistrationHelper {

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;

    @Autowired
    private HealthSubFacilityRepository healthSubFacilityRepository;

    @Override
    protected void fillUserDataBasedOnRole(UserCreateRequest userCreateRequest, User partialFieldUser) {
        validateBlockAndFacility(userCreateRequest);
        Block block = blockRepository.findAllByDistrictCodeAndStateIdAndHealthBlockCode(partialFieldUser.getDistrictCode(), partialFieldUser.getStateId(), userCreateRequest.getBlockCode());
        HealthFacility healthFacility= healthFacilityRepository.findAllByStateIdAndHealthBlockCodeAndHealthFacilityTypeIdAndHealthFacilityCode(partialFieldUser.getStateId(),partialFieldUser.getBlockCode(),partialFieldUser.getFacilityTypeId(),userCreateRequest.getFacilityCode());

        if (block == null) {
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "invalid block in request");
        }
        if(ObjectUtils.isEmpty(healthFacility)){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "invalid facility found");
        }
        HealthSubFacility healthSubFacility = healthSubFacilityRepository.findByHealthFacilityCodeAndHealthFacilitySubCode(userCreateRequest.getFacilityCode(),userCreateRequest.getSubFacilityCode());
        partialFieldUser.setBlockCode(userCreateRequest.getBlockCode());
        partialFieldUser.setFacilityCode(userCreateRequest.getFacilityCode());
        partialFieldUser.setFacilityTypeId(userCreateRequest.getFacilityTypeId());
        if(healthSubFacility!=null){
            partialFieldUser.setSubFacilityCode(userCreateRequest.getSubFacilityCode());
        }

    }

    private void validateBlockAndFacility(UserCreateRequest userCreateRequest){
        if(ObjectUtils.isEmpty(userCreateRequest.getBlockCode()) || ObjectUtils.isEmpty(userCreateRequest.getFacilityCode())){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "blockCode or facilityCode is blank ");
        }
    }

}
