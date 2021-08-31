package com.sdd.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
public class FeedingAssessmentRequest {

  
    @JsonProperty("patientId")
    private Integer patientId;

    @JsonProperty("attachmentToBreastNoGood")
    private Integer attachmentToBreastNoGood;

    @JsonProperty("breastFeedDayCount")
    private Integer breastFeedDayCount;

    @JsonProperty("breastNippleProblem")
    private Integer breastNippleProblem;

    @JsonProperty("mobileId")
    private Integer mobileId;

    @JsonProperty("notSuckingEffectively")
    private Integer notSuckingEffectively;

    @JsonProperty("receivedOtherFood")
    private Integer receivedOtherFood;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("ulcerWhitePatch")
    private Integer ulcerWhitePatch;

    @JsonProperty("visit_no")
    private Integer visitNno;
}
