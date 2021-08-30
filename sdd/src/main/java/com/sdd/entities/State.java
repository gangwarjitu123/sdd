package com.sdd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "state")
@Getter
@Setter
public class State {
    @Id
    @Column(name = "state_id")
    private Integer  id;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "country_id")
    private  Integer countryId;

}
