package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "district")
@Getter
@Setter
public class District {

    @Id
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_Name")
    private String districtName;

    @Column(name = "district_Code")
    private Integer districtCode;

    @Column(name = "mdds_Code")
    private Integer mddsCode;

    @Column(name = "state_id")
    private int stateId;


}
