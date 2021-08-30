package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "district_Code")
    private Integer districtCode;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "block_Code")
    private Integer blockCode;

    @Column(name = "facility_Code")
    private Integer facilityCode;


    @Column(name = "facility_type_id")
    private Integer facilityTypeId;

    @Column(name = "password")
    private String password;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "user_level")
    private Integer level;

    @Column(name = "health_sub_facility_code")
    private Integer subFacilityCode;
}
