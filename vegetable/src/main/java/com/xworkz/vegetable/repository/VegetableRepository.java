package com.xworkz.vegetable.repository;

import com.xworkz.vegetable.entity.VegetableEntity;

import java.util.List;

public interface VegetableRepository {
    boolean save(VegetableEntity entity);
    VegetableEntity readById(int id);
    VegetableEntity findByName(String name);
    VegetableEntity findByColor(String color);
    VegetableEntity findByType(String type);
    List<VegetableEntity> findAll();
    void updateNameById(int id, String newName);
    void deleteById(int id);
}
