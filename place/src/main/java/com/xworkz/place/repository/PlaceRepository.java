package com.xworkz.place.repository;

import com.xworkz.place.entity.PlaceEntity;

import java.util.List;

public interface PlaceRepository {
    boolean save(PlaceEntity entity);
    PlaceEntity readById(int id);
    PlaceEntity findByName(String name);
    PlaceEntity findByCity(String city);
    PlaceEntity findByFamousFor(String famousFor);
    List<PlaceEntity> findAll();
    void updateNameById(int id, String newName);
    void deleteById(int id);
}
