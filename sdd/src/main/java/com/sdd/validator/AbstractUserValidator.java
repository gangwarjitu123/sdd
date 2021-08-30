package com.sdd.validator;

import com.sdd.exception.SDDException;
import com.sdd.request.UserCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

public abstract class AbstractUserValidator {

    public void validate(UserCreateRequest userCreateRequest){
        if(ObjectUtils.isEmpty(userCreateRequest.getEmail()) || ObjectUtils.isEmpty(userCreateRequest.getMobileNumber())
        || ObjectUtils.isEmpty(userCreateRequest.getName()) || ObjectUtils.isEmpty(userCreateRequest.getPassword())
        || ObjectUtils.isEmpty(userCreateRequest.getDistrictCode()) || ObjectUtils.isEmpty(userCreateRequest.getStateId())){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid data in the request,email ,name ,password , mobileNumber District ,block are mandatory fields ");
        }
        validateUser(userCreateRequest);
    }

    protected abstract  void  validateUser(UserCreateRequest userCreateRequest);
}
