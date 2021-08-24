package com.sdd.service;

import com.sdd.request.RoleCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.RoleResponse;

import java.util.List;

public interface RoleService {

    public ApiResponse<List<RoleResponse>> getAllRole();

    ApiResponse<RoleResponse> createRole(RoleCreateRequest roleCreateRequest);
}
