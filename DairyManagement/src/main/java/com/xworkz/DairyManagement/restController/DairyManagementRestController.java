package com.xworkz.DairyManagement.restController;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.entity.AdminEntity;
import com.xworkz.DairyManagement.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class DairyManagementRestController {

    @Autowired
    AdminService adminService;

    public DairyManagementRestController(){

       log.info("Creating DairyManagementRestController");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody AdminDto adminDto) {
        // You will directly get all fields mapped from JSON
        System.out.println("Admin details: " + adminDto);

        adminService.register(adminDto);
        return ResponseEntity.ok("Admin registered successfully: " + adminDto.getAdminName());
    }
}
