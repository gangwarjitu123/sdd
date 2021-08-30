package com.sdd.controller;
import com.sdd.request.RoleCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.RoleResponse;
import com.sdd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRole(){
        //token
        ApiResponse<List<RoleResponse>> roleResponseApiResponse = roleService.getAllRole();
        return new ResponseEntity<>(roleResponseApiResponse, HttpStatus.OK);
    }
    @PostMapping("/createRole")
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@RequestBody RoleCreateRequest roleCreateRequest){
      return new ResponseEntity<>(roleService.createRole(roleCreateRequest),HttpStatus.OK);
    }
}
