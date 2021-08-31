package com.sdd.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoutineAssessmentRequest {

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("visit_no")
    private Integer visitNno;

    @JsonProperty("breathAMinute")
    private Integer breathAMinute;

    @JsonProperty("hadConvulsions")
    private Integer hadConvulsions;

    @JsonProperty("mobileId")
    private Integer mobileId;

    @JsonProperty("movementOnSimulation")
    private Integer movementOnSimulation;

    @JsonProperty("noMovement")
    private Integer noMovement;

    @JsonProperty("notFeedingWell")
    private Integer notFeedingWell;

    @JsonProperty("severeChestIndrawing")
    private Integer severeChestIndrawing;

    @JsonProperty("skinPustules")
    private Integer skinPustules;

    @JsonProperty("temperature")
    private Float temperature;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("umbilicusIndrawingPus")
    private Integer umbilicusIndrawingPus;

    @JsonProperty("umbilicusRed")
    private Integer umbilicusRed;

}
