package com.xworkz.fields.service;

import com.xworkz.fields.entity.EventEntity;

public interface EventService {
    boolean validateAndSave(EventEntity entity);
}
