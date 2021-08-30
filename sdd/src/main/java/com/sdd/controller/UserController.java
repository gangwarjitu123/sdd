package com.sdd.controller;


import com.sdd.login.LoginRequest;
import com.sdd.request.UserCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.LoginResponse;
import com.sdd.response.UserResponse;
import com.sdd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {




    @Autowired
    private UserService userService;



    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<List<UserResponse>>>  getAllUsers(){
        ApiResponse<List<UserResponse>> userResponseApiResponse = userService.getAllUsers();
        ResponseEntity<ApiResponse<List<UserResponse>>> apiResponse = new ResponseEntity<>(userResponseApiResponse, HttpStatus.OK);
        return apiResponse;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        log.info("[login] user login {}",loginRequest);
        return new  ResponseEntity<>(userService.login(loginRequest),HttpStatus.OK);
    }

     @PostMapping("/create")
     public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest){
        ApiResponse<UserResponse> userResponseApiResponse = userService.createUser(userCreateRequest);
        return new ResponseEntity<>(userResponseApiResponse,HttpStatus.OK);
     }


    @PostMapping("/logot")
    public ResponseEntity<ApiResponse<LoginResponse>> logout(@RequestBody LoginRequest loginRequest){
        log.info("[login] user login {}",loginRequest);
        return new  ResponseEntity<>(userService.login(loginRequest),HttpStatus.OK);
    }


}
