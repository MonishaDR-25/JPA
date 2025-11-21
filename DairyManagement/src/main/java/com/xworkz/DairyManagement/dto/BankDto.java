package com.xworkz.DairyManagement.dto;

import lombok.Data;

@Data
public class BankDto {
    private Integer agentId;

    private String bankName;

    private String branchName;

    private String accountHolderName;

    private String accountNumber;

    private String confirmAccountNumber;

    private String ifscCode;

    private String accountType;
}
