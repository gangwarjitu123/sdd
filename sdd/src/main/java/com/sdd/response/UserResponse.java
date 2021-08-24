package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String name;
    private String gmail;
    private String mobileNumber;
    private RoleResponse role;
    private DistrictResponse.DistrictData districtResponse;
    private StateResponse stateResponse;
    private BlockResponse blockResponse;
    private HealthFacilityResponse healthFacilityResponse;
    private HealthFacilityTypeResponse healthFacilityTypeResponse;
}
