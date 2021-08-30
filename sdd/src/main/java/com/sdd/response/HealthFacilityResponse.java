package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthFacilityResponse {
    private Integer healthFacilityId;
    private Integer healthFacilityCode;
    private String healthFacilityName;
    private Integer districtCode;
    private Integer talukaId;
    private Integer healthBlockCode;
    private Integer healthFacilityTypeId;



}
