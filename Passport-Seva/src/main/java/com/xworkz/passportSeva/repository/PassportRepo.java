package com.xworkz.passportSeva.repository;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.entity.PassportEntity;

public interface PassportRepo {
    boolean save(PassportEntity entity);
    String fetchEmail(String email);
}
