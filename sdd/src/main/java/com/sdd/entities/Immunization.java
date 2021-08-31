package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "immunization")
@Getter
@Setter
public class Immunization {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "visit_no")
    private Integer visitNo;

    @Column(name = "is_completed")
    private Integer isCompleted;


    @Column(name = "is_verified")
    private Integer isVerified;

    @Column(name = "mobile_id")
    private Integer mobileId;

    @Column(name = "patient_id")
    private Integer patientId;


    @Column(name = "selected_vaccines")
    private String selectedVaccines;

    @Column(name = "sno")
    private Integer sNo;

    @Column(name = "timestamp")
    private String timeStamp;
}
