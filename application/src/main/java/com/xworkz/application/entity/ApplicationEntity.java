package com.xworkz.application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="application_info")
@NamedQuery(name="findByApplicationName",query="select a from ApplicationEntity a where a.applicationName=:name")
@NamedQuery(name="findApplicationSize",query ="select a from ApplicationEntity a where a.applicationSize=:size")
@NamedQuery(name ="getApplicationEntity" ,query ="select r from ApplicationEntity r " )
@NamedQuery(name="getApplicationName",query = "select a.applicationName from ApplicationEntity a")
@NamedQuery(name = "getApplicationNoOfUsers",query ="select a.noOfUsers from ApplicationEntity a" )
@NamedQuery(name = "getApplicationDate", query = "select a.launchDate from ApplicationEntity a")
@NamedQuery(name="getApplicationNameAndNoOfUsers",query = "select a.applicationName, a.noOfUsers from ApplicationEntity a")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_id")
    private Integer applicationId;

    @Column(name="application_name")
    private String applicationName;

    @Column(name="application_size")
    private String applicationSize;

    @Column(name="company_name")
    private String company;

    @Column(name="no_of_users")
    private Integer noOfUsers;

    @Column(name="ratings")
    private Float ratings;

    @Column(name="launch_date")
    private LocalDate launchDate;
}
