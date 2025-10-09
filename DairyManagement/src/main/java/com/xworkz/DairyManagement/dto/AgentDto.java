package com.xworkz.DairyManagement.dto;

import lombok.Data;

@Data
public class AgentDto {
    private Integer agentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String milkType;

}
