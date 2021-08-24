package com.sdd.service;


import com.sdd.login.LoginRequest;
import com.sdd.request.UserCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.LoginResponse;
import com.sdd.response.UserResponse;

import java.util.List;

public interface UserService {

    public ApiResponse<UserResponse> createUser(UserCreateRequest userCreateRequest);
    public ApiResponse<List<UserResponse>> getAllUsers();
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest);
}
