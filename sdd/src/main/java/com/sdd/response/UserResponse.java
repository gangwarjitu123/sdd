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
    private String stateCode;
    private String stateId;
    private Integer blockCode;
    private String blockName;
    private String facilityName;
    private Integer facilityCode;
    private String subFacilityName;
    private Integer subFacilityCode;

    private DistrictResponse.DistrictData districtResponse;
    private StateResponse stateResponse;
    private BlockResponse blockResponse;
    private HealthFacilityResponse healthFacilityResponse;
    private HealthFacilityTypeResponse healthFacilityTypeResponse;
    private HealthSubFacilityResponse healthSubFacilityResponse;

}
