package com.sdd.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCreateRequest {

    private String name;
    private String email;
    private String mobileNumber;
    private Integer roleId;
    private Integer districtCode;
    private Integer stateId;
    private Integer blockCode;
    private Integer facilityCode;
    private Integer facilityTypeId;
    private String password;
    private Integer subFacilityCode;

}
