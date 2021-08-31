package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "routine_assessment")
@Getter
@Setter
public class RoutineAssessment {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "visit_no")
    private Integer visitNno;

    @Column(name = "breath_a_Minute")
    private Integer breathAMinute;

    @Column(name = "had_convulsions")
    private Integer hadConvulsions;

    @Column(name = "mobile_id")
    private Integer mobileId;

    @Column(name = "movement_on_simulation")
    private Integer movementOnSimulation;

    @Column(name = "no_movement")
    private Integer noMovement;

    @Column(name = "not_feeding_well")
    private Integer notFeedingWell;

    @Column(name = "severe_chest_in_drawing")
    private Integer severeChestIndrawing;

    @Column(name = "skin_pustules")
    private Integer skinPustules;

    @Column(name = "temperature")
    private Float temperature;

    @Column(name = "timestamp")
    private String timeStamp;

    @Column(name = "umbilicus_in_drawing_pus")
    private Integer umbilicusIndrawingPus;

    @Column(name = "umbilicus_red")
    private Integer umbilicusRed;

}
