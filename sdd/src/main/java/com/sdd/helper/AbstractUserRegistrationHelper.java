package com.sdd.helper;

import com.sdd.entities.User;
import com.sdd.request.UserCreateRequest;

public abstract class AbstractUserRegistrationHelper {

    public void  createUserForRegistration(UserCreateRequest userCreateRequest,User partialFieldUser){
        partialFieldUser.setEmail(userCreateRequest.getEmail());
        partialFieldUser.setMobileNumber(userCreateRequest.getMobileNumber());
        partialFieldUser.setName(userCreateRequest.getName());
        partialFieldUser.setPassword(userCreateRequest.getPassword());
        partialFieldUser.setStateId(userCreateRequest.getStateId());
        partialFieldUser.setDistrictCode(userCreateRequest.getDistrictCode());
        return;
    }

    protected abstract void fillUserDataBasedOnRole(UserCreateRequest userCreateRequest,User partialFieldUser);
}
