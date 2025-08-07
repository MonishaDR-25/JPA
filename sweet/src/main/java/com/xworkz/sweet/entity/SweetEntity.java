package com.xworkz.sweet.entity;

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
@Table(name = "sweet_info")
@NamedQuery(name = "findBySweetName", query = "SELECT s FROM SweetEntity s WHERE s.name = :nm")
@NamedQuery(name = "findByMadeBy", query = "SELECT s FROM SweetEntity s WHERE s.madeBy = :mb")
@NamedQuery(name = "findByType", query = "SELECT s FROM SweetEntity s WHERE s.type = :tp")
@NamedQuery(name = "getAllSweets", query = "SELECT s FROM SweetEntity s")
@NamedQuery(name = "getSweetName",query = "select s.name from SweetEntity s")
@NamedQuery(name = "getSweetCost",query = "select s.cost from SweetEntity s")
@NamedQuery(name = "getSweetMadeDate",query = "select s.madeDate from SweetEntity s")
@NamedQuery(name = "getSweetTypeAndIngredients",query = "select s.type,s.ingredients from SweetEntity s")
public class SweetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sweet_id")
    private int id;

    @Column(name = "sweet_name")
    private String name;

    @Column(name = "sweet_type")
    private String type;

    @Column(name = "sweet_cost")
    private double cost;

    @Column(name = "sweet_made_by")
    private String madeBy;

    @Column(name = "sweet_ingredients")
    private String ingredients;

    @Column(name = "sweet_made_date")
    private LocalDate madeDate;
}
