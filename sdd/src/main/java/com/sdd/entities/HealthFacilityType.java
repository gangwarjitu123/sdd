package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "health_facility_type")
@Getter
@Setter
public class HealthFacilityType {

    @Id
    @Column(name = "facility_type_id")
    private Integer facilityTypeId;

    @Column(name = "facility_type_name")
    private String facilityTypeName;

}
