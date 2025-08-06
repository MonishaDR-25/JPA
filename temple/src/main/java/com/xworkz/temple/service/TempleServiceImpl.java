package com.xworkz.temple.service;

import com.xworkz.temple.entity.TempleEntity;
import com.xworkz.temple.repository.TempleRepository;

public class TempleServiceImpl implements TempleService{
    private TempleRepository repository;

    public TempleServiceImpl(TempleRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean validateAndSave(TempleEntity entity) {
        if (entity != null) {
            if (entity.getName() != null && entity.getEntryFee() != null && entity.getLocation() != null &&
                    entity.getFamousFor() != null && entity.getMainGod() != null && entity.getYearBuilt() > 0) {
                return repository.save(entity);
            }
        }
        return false;
    }
}
