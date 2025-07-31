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
@Table(name = "footwear_info")
public class FootwearEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "footwear_name")
    private String name;

    @Column(name = "footwear_brand")
    private String brand;

    @Column(name = "footwear_price")
    private double price;

    @Column(name = "footwear_type")
    private String type;
}
