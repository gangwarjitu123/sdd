package com.sdd.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String name;
    private String email;
    private String mobileNumber;
    private Integer roleId;
    private Integer districtId;
    private Integer stateId;
    private Integer blockId;
    private Integer facilityId;
    private Integer facilityTypeId;
    private String password;

}
