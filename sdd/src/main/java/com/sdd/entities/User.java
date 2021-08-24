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

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "state_id")
    private Integer stateId;

    @Column(name = "block_id")
    private Integer blockId;

    @Column(name = "facility_id")
    private Integer facilityId;


    @Column(name = "facility_type_id")
    private Integer facilityTypeId;

    @Column(name = "password")
    private String password;
}
