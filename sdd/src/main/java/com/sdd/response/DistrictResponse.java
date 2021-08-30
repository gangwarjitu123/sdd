package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictResponse {
    private Integer stateId;
    private String  stateName;
    private List<DistrictData> districts;


    @Builder
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DistrictData {
        private Integer districtId;
        private String  districtName;
        private Integer districtCode;
        private Integer mddsCode;
    }
}
