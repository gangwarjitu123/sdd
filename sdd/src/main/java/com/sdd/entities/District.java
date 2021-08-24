package com.sdd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "state_id")
    @JsonIgnore
    private State state;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Block.class,fetch = FetchType.EAGER,mappedBy = "districts")
    private Set<Block> healthBlock;

    @OneToMany(mappedBy = "districts",cascade = CascadeType.ALL,targetEntity = HealthFacility.class)
    private Set<HealthFacility> healthFacility;


}
