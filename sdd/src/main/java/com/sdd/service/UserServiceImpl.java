package com.sdd.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.*;
import com.sdd.entities.repository.DistrictRepository;
import com.sdd.entities.repository.RoleRepository;
import com.sdd.entities.repository.UserRepository;
import com.sdd.exception.SDDException;
import com.sdd.login.LoginRequest;
import com.sdd.request.UserCreateRequest;
import com.sdd.response.*;
import com.sdd.utils.ResponseUtils;
import com.sdd.validator.AbstractUserValidator;
import com.sdd.validator.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ValidatorFactory validatorFactory;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;





    @Override
    public ApiResponse<UserResponse> createUser( UserCreateRequest userCreateRequest) {
        Optional<Role> roleOptional = roleRepository.findById(userCreateRequest.getRoleId());
        if(!roleOptional.isPresent()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid role");
        }
        AbstractUserValidator abstractUserValidator = validatorFactory.getValidator(roleOptional.get().getRoleName());
        abstractUserValidator.validate(userCreateRequest);
        Optional<District> optionalDistrict =  districtRepository.findById(userCreateRequest.getDistrictId());

        if(!optionalDistrict.isPresent()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid district");
        }
        Role role = roleOptional.get();
        District  district = optionalDistrict.get();
        State state =  optionalDistrict.get().getState();
        if(state==null || state.getId()!=userCreateRequest.getStateId()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid state");
        }
        User user = new User();
        user.setStateId(district.getState().getId());
        user.setDistrictId(district.getDistrictId());
        user.setEmail(userCreateRequest.getEmail());
        user.setName(userCreateRequest.getName());
        user.setMobileNumber(userCreateRequest.getMobileNumber());
        user.setPassword(userCreateRequest.getPassword());
        user.setRoleId(role.getRoleId());

        if(role.getRoleName().equalsIgnoreCase("CHO")){
            validateBlockId(district,userCreateRequest);
            user.setBlockId(userCreateRequest.getBlockId());
            validateFacilityId(district,userCreateRequest);
            user.setFacilityId(userCreateRequest.getFacilityId());

        }else if(role.getRoleName().equalsIgnoreCase("DISTRICT")){
               // nothing will change in user as state and district already populated
        }else if(role.getRoleName().equalsIgnoreCase("BLOCK")){
            validateBlockId(district,userCreateRequest);
            user.setBlockId(userCreateRequest.getBlockId());
        }else if(role.getRoleName().equalsIgnoreCase("STATE")){
           // Not implemented yet
        }
        user = userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setGmail(user.getEmail());
        userResponse.setMobileNumber(user.getMobileNumber());
        userResponse.setName(user.getName());
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoleId(role.getRoleId());
        roleResponse.setRoleName(role.getRoleName());
        userResponse.setRole(roleResponse);
        return ResponseUtils.createSuccessResponse(userResponse, new TypeReference<UserResponse>() {});
    }

    @Override
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        if(!CollectionUtils.isEmpty(userList)){
            List<Integer> districtId = new ArrayList<>();
            List<Integer> roleIds = new ArrayList<>();
            userList.forEach(user -> {
                        districtId.add(user.getDistrictId());
                        roleIds.add(user.getRoleId());
                    });
            List<District> districts = districtRepository.findAllById(districtId);
            List<Role> roleList = roleRepository.findAllById(roleIds);
            Map<Integer,District> districtMap = new HashMap<>();
            AtomicReference<Map<Integer, Block>> blockMap = new AtomicReference<Map<Integer, Block>>();
            AtomicReference<Map<Integer,HealthFacility>> healthFacilityMap = new AtomicReference<Map<Integer,HealthFacility>>();
            AtomicReference<Map<Integer,Role>> roleMap = new AtomicReference<>();
            if(!CollectionUtils.isEmpty(districts)){
              districts.forEach(district -> {
                  districtMap.put(district.getDistrictId(),district);
                  if(!CollectionUtils.isEmpty(district.getHealthBlock())){
                      blockMap.set(district.getHealthBlock().stream().collect(Collectors.toMap(block -> block.getHealthBlockId(), block -> block)));
                  }
                  if(!CollectionUtils.isEmpty(district.getHealthFacility())){
                      healthFacilityMap.set(district.getHealthFacility().stream().collect(Collectors.toMap(healthFacility -> healthFacility.getHealthFacilityId(),healthFacility -> healthFacility)));
                  }
              });
            }
            if(!CollectionUtils.isEmpty(roleList)){
                roleMap.set(roleList.stream().collect(Collectors.toMap(role -> role.getRoleId(),role->role)));
            }
            Map<Integer,Role> roleMapRes =  roleMap.get();

            userList.forEach(user -> {
                 UserResponse userResponse = new UserResponse();
                 userResponse.setName(user.getName());
                 userResponse.setGmail(user.getEmail());
                 userResponse.setMobileNumber(user.getMobileNumber());
                 RoleResponse roleResponse = new RoleResponse();
                 roleResponse.setRoleId(roleMapRes.get(user.getRoleId()).getRoleId());
                 roleResponse.setRoleName(roleMapRes.get(user.getRoleId()).getRoleName());
                 userResponse.setRole(roleResponse);

                DistrictResponse.DistrictData districtData = DistrictResponse.DistrictData.builder().build();
                districtData.setDistrictId(districtMap.get(user.getDistrictId()).getDistrictId());
                districtData.setDistrictName(districtMap.get(user.getDistrictId()).getDistrictName());
                districtData.setDistrictCode(districtMap.get(user.getDistrictId()).getDistrictCode());
                districtData.setMddsCode(districtMap.get(user.getDistrictId()).getMddsCode());
                userResponse.setDistrictResponse(districtData);

                StateResponse stateResponse = StateResponse.builder().build();
                stateResponse.setStateId(districtMap.get(user.getDistrictId()).getState().getId());
                stateResponse.setStateName(districtMap.get(user.getDistrictId()).getState().getStateName());
                userResponse.setStateResponse(stateResponse);
                Map<Integer,HealthFacility> healthFacilityMapRes = healthFacilityMap.get();

                if(healthFacilityMapRes.containsKey(user.getFacilityId())){
                    HealthFacilityResponse healthFacilityResponse = new HealthFacilityResponse();
                    healthFacilityResponse.setHealthFacilityCode(healthFacilityMapRes.get(user.getFacilityId()).getHealthFacilityCode());
                    healthFacilityResponse.setHealthFacilityId(healthFacilityMapRes.get(user.getFacilityId()).getHealthFacilityId());
                    healthFacilityResponse.setTalukaId(healthFacilityMapRes.get(user.getFacilityId()).getTalukaId());
                    userResponse.setHealthFacilityResponse(healthFacilityResponse);
                }


               Map<Integer,Block> blockMapRes =  blockMap.get();
               if(blockMapRes.containsKey(user.getBlockId())){
                   BlockResponse blockResponse = new BlockResponse();
                   blockResponse.setHealthBlockId(blockMapRes.get(user.getBlockId()).getHealthBlockId());
                   blockResponse.setDistrictId(blockMapRes.get(user.getBlockId()).getDistricts().getDistrictId());
                   blockResponse.setMddsCode(blockMapRes.get(user.getBlockId()).getMddsCode());
                   blockResponse.setTalukaId(blockMapRes.get(user.getBlockId()).getTalukaId());
                   blockResponse.setHealthBlockCode(blockMapRes.get(user.getBlockId()).getHealthBlockCode());
                   blockResponse.setHealthBlockName(blockMapRes.get(user.getBlockId()).getHealthBlockName());
                   userResponse.setBlockResponse(blockResponse);

               }


                userResponses.add(userResponse);

            });
        }
        return ResponseUtils.createSuccessResponse(userResponses,new TypeReference<List<UserResponse>>(){});
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(userName);
        if(ObjectUtils.isEmpty(user) || (!password.equals(user.getPassword()))){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "incorrect username or password");
        }
        LoginResponse loginResponse = LoginResponse.builder().build();
        loginResponse.setUseName(user.getName());
        loginResponse.setToken(UUID.randomUUID().toString());
        return ResponseUtils.createSuccessResponse(loginResponse, new TypeReference<LoginResponse>() {});
    }


    private void  validateBlockId(District district,UserCreateRequest userCreateRequest){
        Set<Block> blocks = district.getHealthBlock();
        if(CollectionUtils.isEmpty(blocks)){// throw exception
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid block");
        }
        Optional<Block> healthOptionalBlock = blocks.stream().filter(block -> block.getHealthBlockId()==userCreateRequest.getBlockId()).findFirst();
        if(!healthOptionalBlock.isPresent()) {
            // throw exception
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid block");
        }
    }

    private void validateFacilityId(District district, UserCreateRequest userCreateRequest){
        if(ObjectUtils.isEmpty(userCreateRequest.getFacilityId())){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid facility Id for cho role");
        }
        Optional<HealthFacility> healthFacilityOptional = district.getHealthFacility().stream()
                .filter(healthFacility ->healthFacility.getHealthFacilityId()==userCreateRequest.getFacilityId()).findFirst();
        if(!healthFacilityOptional.isPresent()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid facility Id for cho role");
        }
    }
}
