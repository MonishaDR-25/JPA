package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="product_info")
@NamedQuery(name="findAllProducts",query="SELECT p FROM ProductEntity p")
@NamedQuery(name="countProducts",query="SELECT COUNT(p) FROM ProductEntity p")
@NamedQuery(name="findAllProductsByMilkTypes",query="SELECT p FROM ProductEntity p WHERE p.productType = 'Buy' AND p.active = true AND p.productName LIKE '%Milk%'")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private Double productPrice;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="active")
    private boolean active=true;

    @Column(name="product_type")
    private String productType;
}
