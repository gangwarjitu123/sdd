package com.sdd.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.sdd.entities.*;
import com.sdd.entities.repository.*;
import com.sdd.exception.SDDException;
import com.sdd.helper.AbstractUserRegistrationHelper;
import com.sdd.helper.UserRegistrationFactory;
import com.sdd.jwt.HeaderUtils;
import com.sdd.jwt.JwtUtils;
import com.sdd.login.LoginRequest;
import com.sdd.request.UserCreateRequest;
import com.sdd.response.*;
import com.sdd.utils.ConverterUtils;
import com.sdd.utils.ResponseUtils;
import com.sdd.validator.AbstractUserValidator;
import com.sdd.validator.ValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImpl implements UserService{


    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;

    @Autowired
    private HealthSubFacilityRepository healthSubFacilityRepository;


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HeaderUtils headerUtils;

    @Autowired
    private UserRegistrationFactory userRegistrationFactory;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private FacilityTypeRepository facilityTypeRepository;





    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userRepository.findByEmail(userName);
        if(ObjectUtils.isEmpty(user) || (!password.equals(user.getPassword()))){
            log.info("[login] user login  failed ");
            throw new SDDException(HttpStatus.BAD_REQUEST.value(), "incorrect username or password");
        }
        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUserId(user.getId());
        loginDetails.setLevel(user.getLevel());
        loginDetails.setRoleId(user.getRoleId());
        loginDetails.setCreatedDate(new Date());
        loginDetails.setModifiedDate(new Date());

        LoginDetails loginDetailsResponse = loginRepository.save(loginDetails);
        String jwtToken = jwtUtils.generateJwtToken(loginDetailsResponse);
        LoginResponse loginResponse = LoginResponse.builder().build();
        loginResponse.setUserLevel(user.getLevel());
        loginResponse.setUseName(user.getEmail());
        loginResponse.setToken(jwtToken);
        return ResponseUtils.createSuccessResponse(loginResponse, new TypeReference<LoginResponse>() {});
    }

    @Override
    public ApiResponse<AdminResponse> admin(LoginRequest loginRequest) {
         loginRequest.setUsername(loginRequest.getUserId());
         ApiResponse<LoginResponse> loginResponseApiResponse = login(loginRequest);
         if(loginResponseApiResponse==null || loginResponseApiResponse.getStatus()!=200){
             throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"login failed");
         }
         if(loginResponseApiResponse.getResponse().getUserLevel()!= 0){
             throw new SDDException(HttpStatus.UNAUTHORIZED.value(),"only admin is allow to login by app");
         }
         User adminUserResponse = userRepository.findByEmail(loginResponseApiResponse.getResponse().getUseName());
         AdminResponse adminResponse = new AdminResponse();
         adminResponse.setId(adminUserResponse.getId());
         adminResponse.setUserId(adminUserResponse.getEmail());
         adminResponse.setMobileNumber(adminUserResponse.getMobileNumber());
         adminResponse.setBlockCode(adminUserResponse.getBlockCode());
         adminResponse.setName(adminUserResponse.getName());

         // stateDtaa
        Optional<State> state = stateRepository.findById(adminUserResponse.getStateId());
        adminResponse.setStateCode(adminUserResponse.getStateId());
        adminResponse.setStateName(state.get().getStateName());

        //district data
        adminResponse.setDistrictCode(adminUserResponse.getDistrictCode());
        Optional<District> districtOptional = districtRepository.findByDistrictCodeAndStateId(adminUserResponse.getDistrictCode(),adminUserResponse.getStateId());
        adminResponse.setDistrictName(districtOptional.get().getDistrictName());

         // block data
         Block block = blockRepository.findAllByDistrictCodeAndStateIdAndHealthBlockCode(adminUserResponse.getDistrictCode(),
                 adminUserResponse.getStateId(),adminUserResponse.getBlockCode());
        adminResponse.setBlockName(block.getHealthBlockName());
        adminResponse.setBlockCode(block.getHealthBlockCode());

        HealthFacility healthFacility = healthFacilityRepository.findAllByStateIdAndHealthBlockCodeAndDistrictCodeAndHealthFacilityCode(adminUserResponse.getStateId(),adminUserResponse.getBlockCode(),adminUserResponse.getDistrictCode(),adminUserResponse.getFacilityCode());
        adminResponse.setFacilityCode(healthFacility.getHealthFacilityCode());
        adminResponse.setFacilityName(healthFacility.getHealthFacilityName());

        Optional<HealthFacilityType> healthFacilityTypeOptional = facilityTypeRepository.findById(healthFacility.getHealthFacilityTypeId());

        if(healthFacilityTypeOptional.isPresent()){
            adminResponse.setFacilityTypeName(healthFacilityTypeOptional.get().getFacilityTypeName());
            adminResponse.setFacilityTypeId(healthFacilityTypeOptional.get().getFacilityTypeId());
        }

        if(!ObjectUtils.isEmpty(adminUserResponse.getSubFacilityCode())){
           HealthSubFacility healthSubFacility =  healthSubFacilityRepository.findByHealthFacilityCodeAndHealthFacilitySubCode(adminUserResponse.getFacilityCode(),adminUserResponse.getSubFacilityCode());
           adminResponse.setSubFacilityCode(healthSubFacility.getHealthFacilitySubCode());
           adminResponse.setSubFacilityName(healthSubFacility.getHealthSubCenterName());

          List<Village> villages = villageRepository.findByHealthFacilityIdAndHealthSubFacilityId(adminUserResponse.getFacilityCode(),adminUserResponse.getSubFacilityCode());
          List<VillageResponse> villageResponses = new ArrayList<>();
          if(!CollectionUtils.isEmpty(villages)){
              VillageResponse villageResponse = new VillageResponse();
              villages.forEach(village -> {
                  villageResponse.setId(village.getId());
                  villageResponse.setName(village.getName());
                  villageResponse.setSid(adminUserResponse.getSubFacilityCode());
                  villageResponses.add(villageResponse);
              });
          }
           adminResponse.setVillage(villageResponses);
        }

        return ResponseUtils.createSuccessResponse(adminResponse,new TypeReference<AdminResponse>(){});
    }


    @Override
    public ApiResponse<UserResponse> createUser(UserCreateRequest userCreateRequest) {
        String token  =  headerUtils.getTokeFromHeader();
        String tokenWithUsername = jwtUtils.getUserNameFromJwtToken(token);
        Map<String,Integer> currentLoggedInUser = headerUtils.getUserCurrentDetails(tokenWithUsername);

        if(ObjectUtils.isEmpty(userCreateRequest.getRoleId())){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"ROLE CAN'T BE NULL");
        }
        Optional<Role> roleOptional = roleRepository.findById(userCreateRequest.getRoleId());
        if(!roleOptional.isPresent()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid role");
        }
        if(currentLoggedInUser.get(HeaderUtils.LEVEL)>=roleOptional.get().getLevel()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"logged in user not allowed create this user");
        }
        Optional<District> optionalDistrict =  districtRepository.findByDistrictCodeAndStateId(userCreateRequest.getDistrictCode(),userCreateRequest.getStateId());
        if(!optionalDistrict.isPresent()){
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid district");
        }
        Role role = roleOptional.get();
        User user = new User();
        //validate herirecy
        user.setRoleId(role.getRoleId());
        user.setLevel(role.getLevel());
        user.setCreatedBy(currentLoggedInUser.get(HeaderUtils.USER_ID));
        AbstractUserRegistrationHelper abstractUserRegistrationHelper =  userRegistrationFactory.getUserRegistrationHelper(role);
        abstractUserRegistrationHelper.createUserForRegistration(userCreateRequest,user);
        User duplicates = userRepository.findByEmail(userCreateRequest.getEmail());
        User duplicates1 = userRepository.findByMobileNumber(userCreateRequest.getMobileNumber());
        if(!ObjectUtils.isEmpty(duplicates) || !ObjectUtils.isEmpty(duplicates1)){
            throw new SDDException(HttpStatus.ALREADY_REPORTED.value(), "email or phone already found in db");
        }
        try {
            user = userRepository.save(user);
        }catch (Exception exception){
            exception.printStackTrace();
            throw new SDDException(HttpStatus.BAD_REQUEST.value(),"invalid data found in the request");
        }

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
        String token = headerUtils.getTokeFromHeader();
        String tokenWithUsername = jwtUtils.getUserNameFromJwtToken(token);
        Map<String, Integer> currentLoggedInUser = headerUtils.getUserCurrentDetails(tokenWithUsername);
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> userList = userRepository.findAllByLevelGreaterThanAndCreatedBy(currentLoggedInUser.get(HeaderUtils.LEVEL), currentLoggedInUser.get(HeaderUtils.USER_ID));
            if (!CollectionUtils.isEmpty(userList)) {
                Set<Integer> districtId = new HashSet<>();
                Set<Integer> roleIds = new HashSet<>();
                Set<Integer> stateIds = new HashSet<>();
                Set<Integer> blockIds = new HashSet<>();
                Set<Integer> facilityType = new HashSet<>();
                Set<Integer> facilities = new HashSet<>();
                Set<Integer> subFacilities = new HashSet<>();
                userList.forEach(user -> {
                    districtId.add(user.getDistrictCode());
                    roleIds.add(user.getRoleId());
                    stateIds.add(user.getStateId());
                    if (!ObjectUtils.isEmpty(user.getBlockCode())) {
                        blockIds.add(user.getBlockCode());
                    }
                    if (!ObjectUtils.isEmpty(user.getFacilityTypeId())) {
                        facilityType.add(user.getFacilityTypeId());
                    }
                    if (!ObjectUtils.isEmpty(user.getFacilityCode())) {
                        facilities.add(user.getFacilityCode());
                    }

                    if (!ObjectUtils.isEmpty(user.getSubFacilityCode())) {
                        subFacilities.add(user.getSubFacilityCode());
                    }

                });

                List<Role> roles = roleRepository.findAllById(roleIds);
                Map<Integer, Role> roleMap = ConverterUtils.getRoleVsMap(roles);
                List<State> states = stateRepository.findAllById(stateIds);
                Map<Integer, State> stateMap = ConverterUtils.getStateMap(states);
                userList.forEach(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setName(user.getName());
                    userResponse.setGmail(user.getEmail());
                    userResponse.setMobileNumber(user.getMobileNumber());

                    RoleResponse roleResponse = new RoleResponse();
                    roleResponse.setRoleName(roleMap.get(user.getRoleId()).getRoleName());
                    roleResponse.setRoleId(roleMap.get(user.getRoleId()).getRoleId());
                    userResponse.setRole(roleResponse);

                    StateResponse stateResponse = StateResponse.builder().build();
                    stateResponse.setStateId(stateMap.get(user.getStateId()).getId());
                    stateResponse.setStateName(stateMap.get(user.getStateId()).getStateName());
                    userResponse.setStateResponse(stateResponse);
                    userResponses.add(userResponse);
                });
            }

        return ResponseUtils.createSuccessResponse(userResponses, new TypeReference<List<UserResponse>>() {});
    }
}


