package com.xworkz.module.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "pet_adoption_info")
public class PetAdoptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pet_name")
    private String name;

    @Column(name = "pet_type")
    private String type;

    @Column(name = "pet_age")
    private int age;

    @Column(name = "adopted")
    private String adopted;
}
