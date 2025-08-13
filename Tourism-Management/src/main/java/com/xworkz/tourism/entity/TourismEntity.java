package com.xworkz.tourism.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name="tourism_info")
@NamedQuery(name = "getAllPackage", query = "select a from TourismEntity a")
@NamedQuery(name = "fetchDataByID", query = "select a from TourismEntity a where a.packageId =:id")
public class TourismEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="package_id")
    private Integer packageId;

    @Column(name="package_name")
    private String packageName;

    @Column(name="destination")
    private String destination;

    @Column(name="days")
    private Integer days;

    @Column(name="package_price")
    private Double packagePrice;

    @Column(name="person_count")
    private Integer personCount;
}
