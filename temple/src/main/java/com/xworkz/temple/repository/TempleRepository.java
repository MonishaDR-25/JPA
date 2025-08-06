package com.xworkz.temple.repository;

import com.xworkz.temple.entity.TempleEntity;

public interface TempleRepository {

    boolean save(TempleEntity entity);

    TempleEntity findByName(String name);

    TempleEntity findByLocation(String location);

    TempleEntity findByMainGod(String mainGod);

    TempleEntity findById(int id);

    boolean updateEntryFeeByName(double fee, String name);

    boolean deleteByName(String name);
}
