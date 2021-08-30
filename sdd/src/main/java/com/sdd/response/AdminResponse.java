package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdd.entities.Village;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminResponse {
    private Integer id;
    private String userId;
    private String name;
    private String gmail;
    private String mobileNumber;

    @JsonProperty("state_code")
    private Integer stateCode;

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("block_code")
    private Integer blockCode;

    @JsonProperty("block_name")
    private String blockName;

    @JsonProperty("facility_name")
    private String facilityName;

    @JsonProperty("facility_code")
    private Integer facilityCode;

    @JsonProperty("sub_facility_name")
    private String subFacilityName;

    @JsonProperty("sub_facility_code")
    private Integer subFacilityCode;

    @JsonProperty("district_name")
    private String districtName;

    @JsonProperty("district_code")
    private Integer districtCode ;

    @JsonProperty("facility_type_id")
    private Integer facilityTypeId;

    @JsonProperty("facility_type_name")
    private String facilityTypeName;

    @JsonProperty("village")
    List<VillageResponse> village;

}
