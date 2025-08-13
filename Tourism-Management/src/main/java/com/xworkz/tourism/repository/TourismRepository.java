package com.xworkz.tourism.repository;

import com.xworkz.tourism.entity.TourismEntity;

import java.util.List;

public interface TourismRepository {
    boolean save(TourismEntity entity);
    List<TourismEntity> getAllPackage();
    TourismEntity fetchDataByID(Integer id);
}
