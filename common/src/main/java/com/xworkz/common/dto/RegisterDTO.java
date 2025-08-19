package com.xworkz.common.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class RegisterDTO {

    @NotNull
    private Integer registerId;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[6-9][0-9]{9}$")
    private String phoneNumber;

    @NotBlank
    private String dateOfBirth;

    @NotBlank
    private String gender;

    @NotBlank
    private String state;

    @NotBlank
    private String address;

//    @Size(min = 6)
//    private String password;
//
//    @Size(min = 6)
//    private String confirmPassword;


    @NotBlank
    private String otp;
}
