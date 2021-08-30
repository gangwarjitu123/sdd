package com.sdd.validator.Impl;

import com.sdd.request.UserCreateRequest;
import com.sdd.validator.AbstractUserValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service("DISTRICT:LEVEL")
public class DistrictLevelUserValidator  extends AbstractUserValidator {
    @Override
    protected void validateUser(UserCreateRequest userCreateRequest) {
        if(ObjectUtils.isEmpty(userCreateRequest.getDistrictCode()) ||
                ObjectUtils.isEmpty(userCreateRequest.getStateId())){
            throw new RuntimeException("Invalid Data");
        }
    }
}
