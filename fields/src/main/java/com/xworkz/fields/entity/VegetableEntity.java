package com.xworkz.fields.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "vegetable_info")
@NamedQueries({
        @NamedQuery(name = "getByName", query = "select v from VegetableEntity v where v.name=:nm"),
        @NamedQuery(name = "getByColor", query = "select v from VegetableEntity v where v.color=:cl"),
        @NamedQuery(name = "getByType", query = "select v from VegetableEntity v where v.type=:tp")
})
public class VegetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veg_id")
    private Integer id;

    @Column(name = "veg_name")
    private String name;

    @Column(name = "veg_color")
    private String color;

    @Column(name = "veg_type")
    private String type;

    @Column(name = "veg_cost")
    private Double cost;

    @Column(name = "veg_weight")
    private Double weight;

    @Column(name = "veg_date")
    private LocalDate date;
}
