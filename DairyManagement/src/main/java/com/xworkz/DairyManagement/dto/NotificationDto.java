package com.xworkz.DairyManagement.dto;

import lombok.Data;


@Data
public class NotificationDto {

    private Integer agentId;
    private String agentName;

    private Double totalAmount;
    private Float totalQuantity;

    private String message;
    private String period;   // e.g., "1–15 Jan"
    private boolean overdue;

    private String dueDate;  // display string (16th, 1st, etc.)
}

