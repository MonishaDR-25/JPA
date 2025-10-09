package com.xworkz.DairyManagement.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AdminDto {
    private Integer adminId;

    @NotBlank
    private String adminName;

    @NotBlank
    private String emailId;

    private Long phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    private String profilePicPath;
}
