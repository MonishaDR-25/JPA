package com.xworkz.passportSeva.service;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.entity.PassportEntity;
import com.xworkz.passportSeva.repository.PassportRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportRepo repository;
    public PassportServiceImpl()
    {
        System.out.println("PassportServiceImpl constrructor");
    }


    @Override
    public boolean validateAndSave(PassportDto dto) {

        System.out.println("service data: "+dto);
        if (dto == null) {
            System.out.println("DTO is null");
            return false;
        }

        if (dto.getPassportOffice() == null || dto.getPassportOffice().trim().isEmpty()) {
            System.out.println("Invalid passport office");
            return false;
        }

        if (dto.getGivenName() == null || dto.getGivenName().trim().isEmpty()) {
            System.out.println("Invalid given name");
            return false;
        }

        if (dto.getSurname() == null || dto.getSurname().trim().isEmpty()) {
            System.out.println("Invalid surname");
            return false;
        }

        if (dto.getDateOfBirth() == null ) {
            System.out.println("Invalid date of birth");
            return false;
        }

        if (dto.getEmailId() == null || !dto.getEmailId().contains("@")) {
            System.out.println("Invalid email");
            return false;
        }

        if (dto.getLoginId() == null || dto.getLoginId().length() < 3) {
            System.out.println("Invalid login ID");
            return false;
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 4) {
            System.out.println("Invalid password");
            return false;
        }

        if (dto.getConfirmPassword() == null || !dto.getConfirmPassword().equals(dto.getPassword())) {
            System.out.println("Passwords do not match");
            return false;
        }

        if (dto.getHintQuestion() == null || dto.getHintQuestion().trim().isEmpty()) {
            System.out.println("Invalid hint question");
            return false;
        }

        if (dto.getHintAnswer() == null || dto.getHintAnswer().trim().isEmpty()) {
            System.out.println("Invalid hint answer");
            return false;
        }

        if (dto.getPhoneNumber() == null || String.valueOf(dto.getPhoneNumber()).length() != 10) {
            System.out.println("Invalid phone number");
            return false;
        }
        PassportEntity entity=new PassportEntity();
        BeanUtils.copyProperties(dto,entity);
        return repository.save(entity);
    }

//    @Override
//    public PassportDto fetchEmail(String email) {
//        PassportEntity entity=new PassportEntity();
//        PassportDto dto=new PassportDto();
//        BeanUtils.copyProperties(entity,dto);
//        return dto;
//    }

    @Override
    public String fetchEmail(String email) {
        System.out.println("Created mail in service");

        return repository.fetchEmail(email);
    }

    @Override
    public String fetchLoginId(String loginId) {
        System.out.println("Created loginId in service");
        return repository.fetchLoginId(loginId);
    }


}
