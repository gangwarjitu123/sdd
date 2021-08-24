package com.sdd.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
    @Id
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
    private Set<State> states;
}
