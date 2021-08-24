package com.sdd.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.Role;
import com.sdd.entities.repository.RoleRepository;
import com.sdd.exception.SDDException;
import com.sdd.request.RoleCreateRequest;
import com.sdd.response.ApiResponse;
import com.sdd.response.RoleResponse;
import com.sdd.service.RoleService;
import com.sdd.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public ApiResponse<List<RoleResponse>> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        roleList.forEach(role -> {
           RoleResponse roleResponse = new RoleResponse();
           roleResponse.setRoleId(role.getRoleId());
           roleResponse.setRoleName(role.getRoleName());
           roleResponses.add(roleResponse);
        });

        return ResponseUtils.createSuccessResponse(roleResponses, new TypeReference<List<RoleResponse>>() {});
    }

    @Override
    @Transactional
    public ApiResponse<RoleResponse> createRole(RoleCreateRequest roleCreateRequest) {
        Role role = new Role();
        role.setRoleName(roleCreateRequest.getRoleName());
        try {
            role = roleRepository.save(role);
        }catch (DataIntegrityViolationException duplicateKeyException){
            throw new SDDException(HttpStatus.ALREADY_REPORTED.value(), "duplicate role found");
        }
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoleId(role.getRoleId());
        roleResponse.setRoleName(role.getRoleName());
        return ResponseUtils.createSuccessResponse(roleResponse, new TypeReference<RoleResponse>() {});
    }
}


