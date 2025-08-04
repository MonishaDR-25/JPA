package com.xworkz.fields.repository;

import com.xworkz.fields.entity.EventEntity;

public interface EventRepository {
    boolean save(EventEntity entity);

    EventEntity findByName(String name);

    EventEntity findByLocation(String location);

    EventEntity findByBudget(double budget);

    boolean updateOrganiserById(int id, String organiser);

    boolean deleteById(int id);
}
