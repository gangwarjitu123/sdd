package com.sdd.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "infant_diarrhea_test")
@Getter
@Setter
public class InfantDiarrheaTest {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "visit_no")
    private Integer visitNo;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "mobile_id")
    private Integer mobileId;

    @Column(name = "no_movement")
    private Integer noMovement;

    @Column(name = "rest_less")
    private Integer restLess;

    @Column(name = "skin_pinch_slow")
    private Integer skinPinchSlow;

    @Column(name = "skin_pinch_very_slow")
    private Integer skinPinchVerySlow;

    @Column(name = "sno")
    private Integer sNo;

    @Column(name = "sunken_eyes")
    private Integer sunkenEyes;

    @Column(name = "timestamp")
    private String timeStamp;
}
