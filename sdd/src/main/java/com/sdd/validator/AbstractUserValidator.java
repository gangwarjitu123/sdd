package com.sdd.validator;

import com.sdd.request.UserCreateRequest;
import org.springframework.util.ObjectUtils;

public abstract class AbstractUserValidator {

    public void validate(UserCreateRequest userCreateRequest){
        if(ObjectUtils.isEmpty(userCreateRequest.getEmail()) || ObjectUtils.isEmpty(userCreateRequest.getMobileNumber())
        || ObjectUtils.isEmpty(userCreateRequest.getName()) || ObjectUtils.isEmpty(userCreateRequest.getPassword())){
            throw new RuntimeException("invalid data in the request");
        }
        validateUser(userCreateRequest);
    }

    protected abstract  void  validateUser(UserCreateRequest userCreateRequest);
}
