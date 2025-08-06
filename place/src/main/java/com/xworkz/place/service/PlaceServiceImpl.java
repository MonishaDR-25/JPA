package com.xworkz.place.service;

import com.xworkz.place.entity.PlaceEntity;
import com.xworkz.place.repository.PlaceRepository;

public class PlaceServiceImpl implements PlaceService {

    private PlaceRepository repository;

    public PlaceServiceImpl(PlaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateAndSave(PlaceEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getCity() != null &&
                    entity.getState() != null && entity.getCountry() != null &&
                    entity.getVisitedDate() != null && entity.getFamousFor() != null) {
                return repository.save(entity);
            }
        }
        return false;
    }
}
