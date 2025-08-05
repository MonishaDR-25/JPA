package com.xworkz.event.service;

import com.xworkz.event.entity.EventEntity;

public interface EventService {
    boolean validateSave(EventEntity eventEntity);
}
