package com.sdd.helper;

import com.sdd.entities.Role;
import com.sdd.validator.AbstractUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserRegistrationFactory {

    @Autowired
    Map<String, AbstractUserRegistrationHelper> userRegistrationMap;

    public AbstractUserRegistrationHelper getUserRegistrationHelper(Role role){
        return userRegistrationMap.get(role.getRoleName());
    }
}
