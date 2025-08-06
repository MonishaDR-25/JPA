package com.xworkz.place.service;

import com.xworkz.place.entity.PlaceEntity;

public interface PlaceService {
    boolean validateAndSave(PlaceEntity entity);
}
