package com.sdd.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class PatientCreateRequest {
    private Integer patientId;
    private String ageType;
    private String caseCloseDate;
    private String centerId;
    private String createdAt;
    private Integer createdBy;
    private String deathDate;
    private String dob;
    private String fullName;
    private String gender;
    private float hieghtCm;
    private Boolean isActive;
    private Boolean isPregnant;
    private String mobile;
    private String mobileId;
    private String mode;
    private Integer sno;
    private String spouseName;
    private String status;
    private Integer totalMonths;
    private Integer totalWeeks;
    private String updatedAt;
    private String villageName;
    private Integer weeksOfAge;
    private float weightKg;
    private Integer yearOfAge;
}
