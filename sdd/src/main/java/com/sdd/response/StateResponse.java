package com.sdd.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateResponse implements Serializable {
    private Integer stateId;
    private String stateName;
}
