package com.sdd.controller;


import com.sdd.login.LoginRequest;
import com.sdd.request.UserCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.LoginResponse;
import com.sdd.response.UserResponse;
import com.sdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<List<UserResponse>>>  getAllUsers(){
        ApiResponse<List<UserResponse>> userResponseApiResponse = userService.getAllUsers();
        ResponseEntity<ApiResponse<List<UserResponse>>> apiResponse = new ResponseEntity<>(userResponseApiResponse, HttpStatus.OK);
        return apiResponse;
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        return new  ResponseEntity<>(userService.login(loginRequest),HttpStatus.OK);
    }

     @PostMapping("/create")
     public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserCreateRequest userCreateRequest){
        ApiResponse<UserResponse> userResponseApiResponse = userService.createUser(userCreateRequest);
        return new ResponseEntity<>(userResponseApiResponse,HttpStatus.OK);
     }


}
