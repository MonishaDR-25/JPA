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
@Table(name="shop_info")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Integer id;

    @Column(name="shop_name")
    private String name;

    @Column(name="shop_owner")
    private String owner;

    @Column(name="shop_cost")
    private int cost;
}
