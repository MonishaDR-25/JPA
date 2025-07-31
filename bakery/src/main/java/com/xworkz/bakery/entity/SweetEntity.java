package com.xworkz.bakery.entity;

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
@Table(name="sweet_info")
public class SweetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sweet_name")
    private String name;

    @Column(name = "sweet_type")
    private String type;

    @Column(name = "sweet_price")
    private double price;

    @Column(name = "sweet_color")
    private String color;
}
