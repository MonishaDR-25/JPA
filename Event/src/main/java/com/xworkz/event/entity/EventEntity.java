package com.xworkz.event.entity;

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
@Table(name = "event_info")
@NamedQuery(name = "findByName", query = "SELECT e FROM EventEntity e WHERE e.name = :nm")
@NamedQuery(name = "findByLocation", query = "SELECT e FROM EventEntity e WHERE e.location = :loc")
@NamedQuery(name = "findByBudget", query = "SELECT e FROM EventEntity e WHERE e.budget = :bud")
@NamedQuery(name = "getEventEntity", query = "SELECT e FROM EventEntity e")

public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "event_location")
    private String location;

    @Column(name = "event_budget")
    private double budget;

    @Column(name = "event_organiser")
    private String organiser;

    @Column(name = "event_date")
    private LocalDate date;

    @Column(name = "event_sponsor")
    private String sponsor;

    @Column(name = "event_capacity")
    private int capacity;
}
