package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "village")
public class Village {

    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "district_id")
    private Integer districtId;


    @Column(name = "taluka_id")
    private Integer talukaId;

    @Column(name = "health_facility_id")
    private Integer healthFacilityId;


    @Column(name = "health_sub_facility_id")
    private Integer healthSubFacilityId;

    @Column(name = "village_id")
    private Integer villageId;

    @Column(name = "village_name")
    private String name;
}
