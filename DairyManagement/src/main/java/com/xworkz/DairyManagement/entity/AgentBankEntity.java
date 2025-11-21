package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "agent_bank")
public class AgentBankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name="branch_name")
    private String branchName;

    @Column(name="holder_name")
    private String accountHolderName;

    @Column(name="account_number")
    private String accountNumber;
//    private String confirmAccountNumber;

    @Column(name="ifsc_code")
    private String ifscCode;

    @Column(name="bank_type")
    private String accountType;

}
