package com.sdd.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Data
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer patientId;

    @Column(name = "age_type")
    private String ageType;

    @Column(name ="case_close_date")
    private String caseCloseDate;

    @Column(name = "center_id")
    private String centerId;

    @Column(name="created_at")
    private String createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name="death_date")
    private String deathDate;

    @Column(name = "dob")
    private String dob;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height_cm")
    private float heightCm;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_pregnant")
    private Boolean isPregnant;

    @Column(name = "mobile_number")
    private String mobile;

    @Column(name = "mobile_id")
    private String mobileId;

    @Column(name = "mode")
    private String mode;

    @Column(name = "sno")
    private Integer sno;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "status")
    private String status;

    @Column(name = "total_month")
    private Integer totalMonths;

    @Column(name = "total_weeks")
    private Integer totalWeeks;

    @Column(name = "updated_At")
    private String updatedAt;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "weeks_of_age")
    private Integer weeksOfAge;

    @Column(name = "weight_kg")
    private float weightKg;

    @Column(name = "year_of_age")
    private Integer yearOfAge;

}
