package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.entity.AdminEntity;

public interface AdminService {

    void register(AdminDto adminDto);

    AdminEntity findByEmailEntity(String email);

    boolean checkPassword(String password, String password1);

    void resetFailedAttempts(AdminEntity adminEntity);

    void increaseFailedAttempts(AdminEntity adminEntity);

    void lockAccount(AdminEntity adminEntity);

    void generateUnlockToken(AdminEntity admin);

    void sendUnlockEmail(AdminEntity admin);

    AdminEntity findByUnlockToken(String token);

    void updateAdmin(AdminEntity loggedInAdmin);

    AdminEntity getAdminById(Integer adminId);
}
