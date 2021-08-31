package com.sdd.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
public class InfantDiarrheaTestRequest {

 
    @JsonProperty("visitNo")
    private Integer visitNo;

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("mobileId")
    private Integer mobileId;

    @JsonProperty("noMovement")
    private Integer noMovement;

    @JsonProperty("restless")
    private Integer restLess;

    @JsonProperty("skinPinchSlow")
    private Integer skinPinchSlow;

    @JsonProperty("skinPinchVerySlow")
    private Integer skinPinchVerySlow;

    @JsonProperty("sno")
    private Integer sNo;

    @JsonProperty("sunkenEyes")
    private Integer sunkenEyes;

    @JsonProperty("timeStamp")
    private String timeStamp;
}
