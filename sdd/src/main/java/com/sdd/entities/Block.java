package com.sdd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "health_block")
@Getter
@Setter
public class Block {

    @Id
    @Column(name = "health_block_id")
    private Integer healthBlockId;

    @Column(name = "health_block_code")
    private Integer healthBlockCode;

    @Column(name = "health_block_name")
    private String healthBlockName;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private District districts;

    @Column(name = "taluka_id")
    private Integer talukaId;

    @Column(name = "mdds_code")
    private Integer mddsCode;


}
