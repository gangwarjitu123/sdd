package com.sdd.helper;


import com.sdd.entities.User;
import com.sdd.request.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service("STATE")
public class SateUserRegistrationHelper extends AbstractUserRegistrationHelper {

    @Override
    protected void fillUserDataBasedOnRole(UserCreateRequest userCreateRequest, User partialFieldUser) {

    }
}
