package com.sdd.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
public class InfantJaundiceTestRequest {

    @JsonProperty("visitNo")
    private Integer visitNo;

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("jaudiceAge")
    private Integer jaudiceAge;

    @JsonProperty("mobileId")
    private Integer mobileId;

    @JsonProperty("sno")
    private Integer sNo;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("yellowPalmSole")
    private Integer yellowPalmSole;


}
