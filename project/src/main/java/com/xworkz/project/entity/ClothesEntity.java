package com.xworkz.project.entity;

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
@Table(name = "clothes_info")
public class ClothesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "clothes_name")
    private String name;

    @Column(name = "clothes_brand")
    private String brand;

    @Column(name = "clothes_price")
    private double price;

    @Column(name = "clothes_size")
    private String size;
}
