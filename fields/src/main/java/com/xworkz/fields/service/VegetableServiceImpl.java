package com.xworkz.fields.service;

import com.xworkz.fields.entity.VegetableEntity;
import com.xworkz.fields.repository.VegetableRepoImpl;

public class VegetableServiceImpl implements VegetableService{

    private VegetableRepoImpl repo = new VegetableRepoImpl();

    public boolean validateAndSave(VegetableEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getName().length() >= 3) {
                if (entity.getColor() != null && entity.getColor().length() >= 3) {
                    if (entity.getType() != null && entity.getType().length() >= 3) {
                        if (entity.getCost() != null && entity.getCost() > 0) {
                            if (entity.getWeight() != null && entity.getWeight() > 0) {
                                if (entity.getDate() != null) {
                                    return repo.save(entity);
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
