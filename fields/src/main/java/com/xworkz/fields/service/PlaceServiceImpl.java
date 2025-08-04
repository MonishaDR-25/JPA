package com.xworkz.fields.service;

import com.xworkz.fields.entity.PlaceEntity;
import com.xworkz.fields.repository.PlaceRepository;

public class PlaceServiceImpl implements PlaceService{

    private PlaceRepository repository;

    public PlaceServiceImpl(PlaceRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateAndSave(PlaceEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getCity() != null && entity.getState() != null &&
                    entity.getCountry() != null && entity.getFamousFor() != null && entity.getPincode() != null &&
                    entity.getPincode() > 100000) {
                return repository.save(entity);
            }
        }
        return false;
    }
}
