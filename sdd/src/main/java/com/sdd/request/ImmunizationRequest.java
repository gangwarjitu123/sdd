package com.sdd.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
public class ImmunizationRequest {


    @JsonProperty("visit_no")
    private Integer visitNo;

    @JsonProperty("isCompleted")
    private Integer isCompleted;


    @JsonProperty("isVerified")
    private Integer isVerified;

    @JsonProperty("mobileId")
    private Integer mobileId;

    @JsonProperty("patientId")
    private Integer patientId;


    @JsonProperty("selectedVaccines")
    private String selectedVaccines;

    @JsonProperty("sno")
    private Integer sNo;

    @JsonProperty("timeStamp")
    private String timeStamp;
}
