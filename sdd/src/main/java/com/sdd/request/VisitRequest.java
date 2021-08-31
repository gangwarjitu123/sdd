package com.sdd.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VisitRequest {


    @JsonProperty("visit_no")
    private Integer visitNo;

    @JsonProperty("age_in_days")
    private Integer ageInDays;

    @JsonProperty("age_in_weeks")
    private Integer ageInWeeks;

    @JsonProperty("age_in_years")
    private Integer ageInYears;

    @JsonProperty("currentStatus")
    private Integer currentStatus;

    @JsonProperty("dangerSign")
    private String dangerSign;

    @JsonProperty("followUpDate")
    private String followUpDate;

    @JsonProperty("ga_weeks")
    private String gaWeeks;

    @JsonProperty("mobileId")
    private String mobileId;

    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("referred")
    private Integer referred;

    @JsonProperty("referred_location")
    private String referredLocation;

    @JsonProperty("symptoms")
    private String symptoms;

    @JsonProperty("temperature")
    private Float temperature;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("visit_date")
    private String visitDate;

}
