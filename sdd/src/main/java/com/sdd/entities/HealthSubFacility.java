package com.sdd.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "health_sub_facility")
@Data
public class HealthSubFacility {

    @Id
    @Column(name = "health_sub_facility_id")
    private Integer healthSubFacilityId;

    @Column(name = "district_Code")
    private Integer districtCode;

    @Column(name = "taluka_id")
    private Integer talukaId;

    @Column(name = "health_facility_Code")
    private Integer healthFacilityCode;

    @Column(name = "health_sub_facility_code")
    private Integer healthFacilitySubCode;

    @Column(name = "health_sub_center_name")
    private String healthSubCenterName;

    @Column(name = "state_id")
    private Integer stateId;
}
