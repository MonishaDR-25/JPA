package com.xworkz.soap.entity;

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
@Table(name="soap_details")
@NamedQuery(name="findAllEntity",query = "select a from SoapEntity a")
@NamedQuery(name = "findById",query = "select a from SoapEntity a where a.soapId=:id")
@NamedQuery(name="updateByID",query = "update SoapEntity a set a.name=:name,a.brand=:brand,a.color=:color," +
        "a.cost=:cost where a.soapId=:id")
@NamedQuery(name = "deleteById",query = "delete SoapEntity a where a.soapId=:id")
public class SoapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="soap_id")
    private Integer soapId;

    @Column(name="soap_name")
    private String name;

    @Column(name="soap_brand")
    private String brand;

    @Column(name="soap_color")
    private String color;

    @Column(name="soap_cost")
    private Double cost;
}
