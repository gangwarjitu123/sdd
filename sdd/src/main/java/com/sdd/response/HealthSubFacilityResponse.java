package com.sdd.response;

import lombok.Data;

@Data
public class HealthSubFacilityResponse {
    private int healthSubFacilityCode;
    private int healthFacilityCode;
    private String healthFacilityCenterName;
}
