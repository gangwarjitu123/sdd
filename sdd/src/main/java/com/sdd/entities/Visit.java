package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "visit")
@Getter
@Setter
public class Visit {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "visit_no")
    private Integer visitNo;

    @Column(name = "age_in_days")
    private Integer ageInDays;

    @Column(name = "age_in_weeks")
    private Integer ageInWeeks;

    @Column(name = "age_in_years")
    private Integer ageInYears;

    @Column(name = "current_status")
    private Integer currentStatus;

    @Column(name = "danger_sign")
    private String dangerSign;

    @Column(name = "follow_up_date")
    private String followUpDate;

    @Column(name = "ga_weeks")
    private Integer gaWeeks;

    @Column(name = "mobile_id")
    private String mobileId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "referred_id")
    private Integer referred;

    @Column(name = "referred_location")
    private String referredLocation;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "time_stamp")
    private String timeStamp;

    @Column(name = "patient_Type")
    private String patientType;

    @Column(name = "visit_date")
    private String visitDate;
}