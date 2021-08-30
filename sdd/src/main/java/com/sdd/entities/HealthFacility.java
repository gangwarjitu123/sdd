package com.sdd.entities;
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

    @Column(name = "district_code")
    private Integer districtCode;

    @Column(name = "taluka_id")
    private Integer talukaId;

    @Column(name = "health_block_code")
    private Integer healthBlockCode;


    @Column(name = "health_facility_type_id")
    private Integer healthFacilityTypeId;

    @Column(name = "state_id")
    private Integer stateId;

}
