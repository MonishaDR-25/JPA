package com.xworkz.fields.service;

import com.xworkz.fields.entity.EventEntity;
import com.xworkz.fields.repository.EventRepoImpl;
import com.xworkz.fields.repository.EventRepository;

public class EventServiceImpl implements EventService{

    private EventRepository repo = new EventRepoImpl();

    @Override
    public boolean validateAndSave(EventEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getLocation() != null && entity.getOrganiser() != null
                    && entity.getSponsor() != null && entity.getDate() != null && entity.getBudget() > 0
                    && entity.getCapacity() > 0) {
                return repo.save(entity);
            }
        }
        return false;
    }
}

