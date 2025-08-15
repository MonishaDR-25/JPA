package com.xworkz.passportSeva.controller;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PassportController {

    @Autowired
    private PassportService service;

    public PassportController(){
        System.out.println("Running PassportController");
    }

    @GetMapping("redirectToPassport")
    public String setIndex(){
        System.out.println("created Index..");
        return "Passport";
    }


    @PostMapping("register")
    public String getRegister(PassportDto dto, Model model){
        System.out.println("Dto Registered");
        System.out.println(dto);
        if(service.validateAndSave(dto)){
            model.addAttribute("message","Submitted");
        }
        else {
            model.addAttribute("message","Not Submitted");
            model.addAttribute("dto",dto);
        }
        return "Passport";
    }



}
