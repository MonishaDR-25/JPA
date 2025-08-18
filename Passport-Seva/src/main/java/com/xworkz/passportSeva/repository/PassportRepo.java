package com.xworkz.passportSeva.repository;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.entity.PassportEntity;

public interface PassportRepo {
    boolean save(PassportEntity entity);
    String fetchEmail(String email);
    String fetchLoginId(String loginId);
    String fetchPhoneNumber(String phoneNumber);
}
