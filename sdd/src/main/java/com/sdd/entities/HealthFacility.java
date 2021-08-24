package com.sdd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "health_facility")
@Getter
@Setter
public class HealthFacility {

    @Id
    @Column(name = "health_facility_id")
    private Integer healthFacilityId;
    @Column(name = "health_facility_code")
    private Integer healthFacilityCode;
    @Column(name = "health_facility_name")
    private String healthFacilityName;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "taluka_id")
    private Integer talukaId;

    @Column(name = "health_block_id")
    private Integer healthBlockId;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "district_id",insertable = false,updatable = false)
    private District districts;

    @OneToOne
    @JoinColumn(name = "health_facility_type_id")
    private HealthFacilityType healthFacilityType;

}
