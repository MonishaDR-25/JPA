package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;

public interface RegisterRepository {

    boolean save(RegisterEntity entity);
    String checkExistingEmail(String email);
    String checkExistingPhoneNumber(String phoneNumber);
    RegisterEntity getUserDetailsByEmail(String email);
}
