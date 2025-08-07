package com.xworkz.vegetable.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "vegetable_info")
@NamedQuery(name = "findByVegetableName", query = "SELECT v FROM VegetableEntity v WHERE v.name = :nm")
@NamedQuery(name = "findByColor", query = "SELECT v FROM VegetableEntity v WHERE v.color = :clr")
@NamedQuery(name = "findByType", query = "SELECT v FROM VegetableEntity v WHERE v.type = :tp")
@NamedQuery(name = "getAllVegetables", query = "SELECT v FROM VegetableEntity v")
@NamedQuery(name = "getVegetableName",query = "select v.name from VegetableEntity v")
@NamedQuery(name = "getVegetableCost",query = "select v.cost from VegetableEntity v")
@NamedQuery(name = "getVegetableArrivalDate",query = "select v.arrivalDate from VegetableEntity v")
@NamedQuery(name = "getVegetableColorAndOrigin",query = "select v.color,v.origin from VegetableEntity v")
public class VegetableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veg_id")
    private int id;

    @Column(name = "veg_name")
    private String name;

    @Column(name = "veg_type")
    private String type;

    @Column(name = "veg_color")
    private String color;

    @Column(name = "veg_origin")
    private String origin;

    @Column(name = "veg_cost")
    private double cost;

    @Column(name = "veg_arrival_date")
    private LocalDate arrivalDate;
}
