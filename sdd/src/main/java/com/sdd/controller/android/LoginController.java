package com.sdd.controller.android;

import com.sdd.login.LoginRequest;
import com.sdd.response.AdminResponse;
import com.sdd.response.ApiResponse;
import com.sdd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminResponse>> login(@RequestBody LoginRequest loginRequest){
        log.info("[admin login] {}",loginRequest);
        return new  ResponseEntity<>(userService.admin(loginRequest), HttpStatus.OK);
    }
}
