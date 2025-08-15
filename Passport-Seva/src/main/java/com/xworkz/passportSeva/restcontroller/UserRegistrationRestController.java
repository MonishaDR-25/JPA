package com.xworkz.passportSeva.restcontroller;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserRegistrationRestController {

    @Autowired
     PassportService service;

    @GetMapping("/loginemail")
    public String loginemailexist(@RequestParam("email") String email){
        System.out.println("loginemailexist in rest controller");
       String result=service.fetchEmail(email);
        System.out.println("fetch email: "+email);
        System.out.println("result: "+result);
        if(result==null){
          return " ";
        }
        return "email exists";
    }

}
