package com.xworkz.event.service;

import com.xworkz.event.entity.EventEntity;
import com.xworkz.event.repository.EventRepository;

public class EventServiceImpl implements EventService {

    private EventRepository repo;

    public EventServiceImpl(EventRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean validateSave(EventEntity eventEntity) {
        if (eventEntity != null) {
            if (eventEntity.getName() != null && eventEntity.getLocation() != null &&
                    eventEntity.getOrganiser() != null && eventEntity.getSponsor() != null &&
                    eventEntity.getDate() != null && eventEntity.getBudget() > 0 &&
                    eventEntity.getCapacity() > 0) {

                return repo.save(eventEntity);
            }
        }
        return false;
    }
}
