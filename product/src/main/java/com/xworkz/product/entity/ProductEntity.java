package com.xworkz.product.entity;


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
@Table(name="product_info")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private double cost;

    @Column(name = "product_mfd")
    private String mfd;

    @Column(name = "product_company")
    private String company;
}
