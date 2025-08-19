package com.xworkz.common.service;

import com.xworkz.common.dto.RegisterDTO;
import com.xworkz.common.entity.RegisterEntity;
import com.xworkz.common.repository.RegisterRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegisterServiceImpl() {
        System.out.println("RegisterServiceImpl constructor");
    }

    private boolean validate(RegisterDTO registerDTO) {
        System.out.println("validate details in RegisterServiceImpl");
        if (registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            System.out.println("Password is equal");
        } else {
            System.out.println("Password is not equal");
            return false;
        }
        return true;
    }

    @Override
    public boolean save(RegisterDTO registerDTO) {
        System.out.println("save method in RegisterServiceImpl");
        System.out.println("service data: "+registerDTO);
        if (validate(registerDTO)) {
            System.out.println("All details are valid");
            RegisterEntity entity = new RegisterEntity();
            BeanUtils.copyProperties(registerDTO, entity);
            entity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            entity.setConfirmPassword(passwordEncoder.encode(registerDTO.getConfirmPassword()));
            entity.setIsActive(true);
            return repository.save(entity);
        }
        return false;
    }

    @Override
    public String checkExistingPhoneNumber(String phoneNumber) {
        System.out.println("checkExistingPhoneNumber method in service");
        return repository.checkExistingPhoneNumber(phoneNumber);
    }

    @Override
    public String checkExistingEmail(String email) {
        System.out.println("checkExistingEmail method in service");
        return repository.checkExistingEmail(email);
    }

    @Override
    public RegisterDTO getUserDetails(String email, String password) {
        System.out.println("getUserDetails method in service");
        RegisterEntity entity=repository.getUserDetailsByEmail(email);
        if(passwordEncoder.matches(password,entity.getPassword()))
        {
            RegisterDTO dto=new RegisterDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }
}
