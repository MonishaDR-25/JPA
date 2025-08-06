package com.xworkz.temple.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "temple_info")
@NamedQueries({
        @NamedQuery(name = "findByName", query = "SELECT t FROM TempleEntity t WHERE t.name = :nm"),
        @NamedQuery(name = "findByLocation", query = "SELECT t FROM TempleEntity t WHERE t.location = :loc"),
        @NamedQuery(name = "findByMainGod", query = "SELECT t FROM TempleEntity t WHERE t.mainGod = :god"),
        @NamedQuery(name = "getAllTemples", query = "SELECT t FROM TempleEntity t")
})
public class TempleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temple_id")
    private Integer id;

    @Column(name = "temple_name")
    private String name;

    @Column(name = "temple_location")
    private String location;

    @Column(name = "main_god")
    private String mainGod;

    @Column(name = "famous_for")
    private String famousFor;

    @Column(name = "year_built")
    private Integer yearBuilt;

    @Column(name = "entry_fee")
    private Double entryFee;
}
