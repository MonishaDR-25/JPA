package com.xworkz.passportSeva.service;

import com.xworkz.passportSeva.dto.PassportDto;

public interface PassportService {
    boolean validateAndSave(PassportDto dto);

    String fetchEmail(String email);
    Stri
}
