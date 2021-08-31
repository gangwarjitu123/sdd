package com.sdd.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "feeding_assessment")
@Getter
@Setter
public class FeedingAssessment {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "attachment_to_breast_no_good")
    private Integer attachmentToBreastNoGood;

    @Column(name = "breast_feed_day_count")
    private Integer breastFeedDayCount;

    @Column(name = "breast_nipple_problem")
    private Integer breastNippleProblem;

    @Column(name = "mobile_id")
    private Integer mobileId;

    @Column(name = "not_sucking_effectively")
    private Integer notSuckingEffectively;

    @Column(name = "received_other_food")
    private Integer receivedOtherFood;

    @Column(name = "timestamp")
    private String timeStamp;

    @Column(name = "ulcer_white_patch")
    private Integer ulcerWhitePatch;

    @Column(name = "visit_no")
    private Integer visitNno;
}
