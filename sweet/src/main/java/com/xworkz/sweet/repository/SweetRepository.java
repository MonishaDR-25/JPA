package com.xworkz.sweet.repository;

import com.xworkz.sweet.entity.SweetEntity;

import java.util.List;

public interface SweetRepository {
    boolean save(SweetEntity entity);
    SweetEntity readById(int id);
    SweetEntity findByName(String name);
    SweetEntity findByMadeBy(String madeBy);
    SweetEntity findByType(String type);
    List<SweetEntity> findAll();
    void updateNameById(int id, String newName);
    void deleteById(int id);
}
