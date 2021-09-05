package com.sdd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitExcelResponse {

    private Integer id;
    private Integer visitNo;
    private Integer ageInDays;
    private Integer ageInWeeks;
    private Integer ageInYears;
    private Integer currentStatus;
    private String dangerSign;
    private String followUpDate;
    private Integer gaWeeks;
    private String mobileId;
    private Integer patientId;
    private Integer referred;
    private String referredLocation;
    private String symptoms;
    private Float temperature;
    private String timeStamp;
    private String patientType;
    private String visitDate;
    private Integer jaudiceAge;
    private Integer sNo;
    private Integer yellowPalmSole;
    private Integer noMovement;
    private Integer restLess;
    private Integer skinPinchSlow;
    private Integer skinPinchVerySlow;
    private Integer sunkenEyes;
    private Integer isCompleted;
    private Integer isVerified;
    private String selectedVaccines;
    private Integer attachmentToBreastNoGood;
    private Integer breastFeedDayCount;
    private Integer breastNippleProblem;
    private Integer notSuckingEffectively;
    private Integer receivedOtherFood;
    private Integer ulcerWhitePatch;
    private Integer breathAMinute;
    private Integer hadConvulsions;
    private Integer movementOnSimulation;
    private Integer notFeedingWell;
    private Integer severeChestIndrawing;
    private Integer skinPustules;
    private Integer umbilicusIndrawingPus;
    private Integer umbilicusRed;

}
