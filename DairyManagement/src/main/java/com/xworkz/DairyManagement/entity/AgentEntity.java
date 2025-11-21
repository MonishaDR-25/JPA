package com.xworkz.DairyManagement.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "agent_info")
@NamedQuery(name = "findAllAgents",query = "select a from AgentEntity a")
@NamedQuery(name = "countAgents", query = "SELECT COUNT(a) FROM AgentEntity a")
public class AgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agent_id")
    private Integer agentId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="milk_type")
    private String milkType;

    @Column(name = "profile_photo_path")
    private String profilePhotoPath;




}
