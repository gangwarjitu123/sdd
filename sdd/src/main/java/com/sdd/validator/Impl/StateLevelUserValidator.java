package com.sdd.validator.Impl;

import com.sdd.request.UserCreateRequest;
import com.sdd.validator.AbstractUserValidator;
import org.springframework.stereotype.Service;

@Service("STATE:LEVEL")
public class StateLevelUserValidator extends AbstractUserValidator {
    @Override
    protected void validateUser(UserCreateRequest userCreateRequest) {

    }
}
