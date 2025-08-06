package com.xworkz.sweet.service;

import com.xworkz.sweet.entity.SweetEntity;
import com.xworkz.sweet.repository.SweetRepository;

public class SweetServiceImpl implements SweetService {

    private SweetRepository repository;

    public SweetServiceImpl(SweetRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateAndSave(SweetEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getType() != null &&
                    entity.getMadeBy() != null && entity.getIngredients() != null &&
                    entity.getMadeDate() != null && entity.getCost() > 0) {
                return repository.save(entity);
            }
        }
        return false;
    }
}
