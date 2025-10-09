package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="admin_audit")
public class AdminAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="audit_id")
    private Integer auditId;

    @OneToOne
    @JoinColumn(name="admin_id", referencedColumnName = "admin_id")
    private AdminEntity admin;

    @Column(name="admin_name")
    private String adminName;

    @Column(name="login_time")
    private LocalDateTime loginTime;

    @Column(name="logout_time")
    private LocalDateTime logoutTime;





}
