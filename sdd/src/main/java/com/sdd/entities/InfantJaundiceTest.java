package com.sdd.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "infant_jaundice_test")
@Getter
@Setter
public class InfantJaundiceTest {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "visit_no")
    private Integer visitNo;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "jaudice_age")
    private Integer jaudiceAge;

    @Column(name = "mobile_id")
    private Integer mobileId;

    @Column(name = "sno")
    private Integer sNo;

    @Column(name = "timestamp")
    private String timeStamp;

    @Column(name = "yellow_palm_sole")
    private Integer yellowPalmSole;


}
