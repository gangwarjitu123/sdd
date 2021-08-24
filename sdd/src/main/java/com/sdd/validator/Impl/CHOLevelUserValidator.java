package com.sdd.validator.Impl;

import com.sdd.request.UserCreateRequest;
import com.sdd.validator.AbstractUserValidator;
import org.springframework.stereotype.Service;

@Service("CHO")
public class CHOLevelUserValidator extends AbstractUserValidator {
    @Override
    protected void validateUser(UserCreateRequest userCreateRequest) {

    }
}
