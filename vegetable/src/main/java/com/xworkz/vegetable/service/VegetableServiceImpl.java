package com.xworkz.vegetable.service;

import com.xworkz.vegetable.entity.VegetableEntity;
import com.xworkz.vegetable.repository.VegetableRepository;

public class VegetableServiceImpl implements VegetableService {

    private VegetableRepository repository;

    public VegetableServiceImpl(VegetableRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateAndSave(VegetableEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getType() != null && entity.getColor() != null &&
                    entity.getOrigin() != null && entity.getArrivalDate() != null && entity.getCost() > 0) {
                return repository.save(entity);
            }
        }
        return false;
    }
}
