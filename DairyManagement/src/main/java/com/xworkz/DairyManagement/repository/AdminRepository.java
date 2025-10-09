package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.AdminEntity;

public interface AdminRepository {
    void save(AdminEntity entity);

    AdminEntity findByEmail(String email);

    void updateAdmin(AdminEntity adminEntity);

    AdminEntity findByUnlockToken(String token);

    AdminEntity findById(Integer adminId);
}
