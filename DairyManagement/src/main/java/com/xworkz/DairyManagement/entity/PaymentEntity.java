package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "payment_info")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "agent_id")
    private Integer agentId;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "paid_date")
    private LocalDate paidDate;
}
