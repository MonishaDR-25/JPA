package com.xworkz.DairyManagement.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductCollectionDto {

    private Integer productCollectionId;
    private AgentDto agent;
    private AdminDto admin;


    private String phoneNumber;


    private String typeOfMilk;


    private Double price;


    private Float quantity;


    private Double totalAmount;


    private LocalDate collectedAt;

}
