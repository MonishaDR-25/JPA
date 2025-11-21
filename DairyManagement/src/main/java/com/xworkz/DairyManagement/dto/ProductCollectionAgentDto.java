package com.xworkz.DairyManagement.dto;

import lombok.Data;

@Data
public class ProductCollectionAgentDto {
 private Integer productCollectionId;
    //Agent Details
    private String agentName;
    private String agentEmail;
    private String agentPhone;
    private String agentAddress;

    //Product Details
   private String typeOfMilk;
   private Double price;
   private Float quantity;
   private Double totalAmount;

}
