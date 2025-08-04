package com.xworkz.fields.repository;

import com.xworkz.fields.entity.PlaceEntity;

public interface PlaceRepository {
    boolean save(PlaceEntity entity);

    PlaceEntity findByName(String name);

    PlaceEntity findByCity(String city);

    PlaceEntity findByFamousFor(String famousFor);

    boolean updateCityById(String city, int id);

    boolean deleteById(int id);
}

