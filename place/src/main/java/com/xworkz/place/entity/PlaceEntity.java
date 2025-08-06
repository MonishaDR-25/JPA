package com.xworkz.place.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "place_info")
@NamedQuery(name = "findByPlaceName", query = "SELECT p FROM PlaceEntity p WHERE p.name = :nm")
@NamedQuery(name = "findByCity", query = "SELECT p FROM PlaceEntity p WHERE p.city = :ct")
@NamedQuery(name = "findByFamousFor", query = "SELECT p FROM PlaceEntity p WHERE p.famousFor = :ff")
@NamedQuery(name = "getAllPlaces", query = "SELECT p FROM PlaceEntity p")
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int id;

    @Column(name = "place_name")
    private String name;

    @Column(name = "place_city")
    private String city;

    @Column(name = "place_state")
    private String state;

    @Column(name = "place_country")
    private String country;

    @Column(name = "place_visited_date")
    private LocalDate visitedDate;

    @Column(name = "place_famous_for")
    private String famousFor;
}
