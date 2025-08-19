package com.xworkz.common.service;

import com.xworkz.common.dto.RegisterDTO;

public interface RegisterService {

    boolean save(RegisterDTO registerDTO);
    String checkExistingEmail(String email);
    String checkExistingPhoneNumber(String phoneNumber);
    RegisterDTO getUserDetails(String email, String password);
}
