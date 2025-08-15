package com.xworkz.passportSeva.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportDto {
    private Integer passportId;
    private String passportOffice;
    private String givenName;
    private String surname;
    private String dateOfBirth;
    private String emailId;
    private String loginId;
    private String password;
    private String confirmPassword;
    private String hintQuestion;
    private String hintAnswer;
    private Long phoneNumber;

}
