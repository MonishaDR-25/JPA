package com.xworkz.event.repository;

import com.xworkz.event.entity.EventEntity;

import java.util.List;

public interface EventRepository {
    boolean save(EventEntity eventEntity);
    EventEntity readById(int id);
    EventEntity getEventByName(String name);
    EventEntity getEventByLocation(String location);
    EventEntity getEventByBudget(double budget);
    List<EventEntity> getEvent();
    void updateEventById(int id, String newName);
    void deleteById(int id);
}
