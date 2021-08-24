package com.sdd.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ValidatorFactory {

    @Autowired
    Map<String,AbstractUserValidator> userValidatorMap;

    public AbstractUserValidator  getValidator(String key){
        return userValidatorMap.get(key);
    }
}
