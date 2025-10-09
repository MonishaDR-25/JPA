package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "admin_info")
@NamedQuery(name = "findByEmail",query = "select a from AdminEntity a where a.emailId=:email")
@NamedQuery(name="findByUnlockToken",query = "select a from AdminEntity a where a.unlockToken=:token")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="admin_id")
    private Integer adminId;

    @Column(name="admin_name")
    private String adminName;

    @Column(name="admin_email")
    private String emailId;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="password")
    private String password;

    @Column(name="confirm_password")
    private String confirmPassword;

    @Column(name="failed_attempts")
    private int failedAttempts=0;

    @Column(name="account_locked")
    private boolean accountLocked=false;

    @Column(name="locked_at")
    private LocalDateTime lockedAt;

    @Column(name="unlock_token")
    private String unlockToken;

    @Column(name="unlockToken_expiry")
    private LocalDateTime unlockTokenExpiry;

    @Column(name="profile_pic")
    private String profilePicPath;

    @OneToOne(mappedBy = "admin",cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private AdminAuditEntity audit;
}
