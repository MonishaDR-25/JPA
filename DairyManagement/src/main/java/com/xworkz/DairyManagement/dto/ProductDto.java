package com.xworkz.DairyManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProductDto {

    private Integer productId;

    private String productName;

    private Double productPrice;

    private String createdBy;

    private LocalDateTime createdAt;

    private String updatedBy;

    private LocalDateTime updatedAt;

    private boolean active=true;

    private String productType;
}
