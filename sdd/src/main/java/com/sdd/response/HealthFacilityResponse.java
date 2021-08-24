package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthFacilityResponse {
    private Integer healthFacilityId;
    private Integer healthFacilityCode;
    private String healthFacilityName;
    private Integer districtId;
    private Integer talukaId;
    private Integer healthBlockId;
    private Integer healthFacilityTypeId;
    private HealthFacilityTypeResponse healthFacilityTypeData;
}
