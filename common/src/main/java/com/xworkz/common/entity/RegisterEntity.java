package com.xworkz.common.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="common_info")
@NamedQuery(name = "checkExistingEmail",query ="select a.email from RegisterEntity a where a.email=:email and a.isActive=true")
@NamedQuery(name = "checkExistingPhoneNumber",query ="select a.phoneNumber from RegisterEntity a where a.phoneNumber=:phoneNumber and a.isActive=true")
@NamedQuery(name = "getDetailsByEmail",query = "select a from RegisterEntity a where a.email=:email and a.isActive=true")
public class RegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Integer registerId;
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "confirmPassword")
    private String confirmPassword;

    @Column(name = "is_active")
    private Boolean isActive;
}
