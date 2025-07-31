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
@Table(name="soap_info")
public class SoapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="soap_name")
    private String name;

    @Column(name="soap_brand")
    private String brand;

    @Column(name="soap_price")
    private double price;

    @Column(name="soap_color")
    private String color;
}
