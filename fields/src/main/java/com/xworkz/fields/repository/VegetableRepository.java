package com.xworkz.fields.repository;

import com.xworkz.fields.entity.VegetableEntity;

public interface VegetableRepository {
    boolean save(VegetableEntity entity);

    VegetableEntity findByName(String name);

    VegetableEntity findByColor(String color);

    VegetableEntity findByType(String type);

    boolean updateCostById(int id, double cost);

    boolean deleteById(int id);
}
