package com.xworkz.passportSeva.restcontroller;

import com.xworkz.passportSeva.dto.PassportDto;
import com.xworkz.passportSeva.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
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

    @GetMapping("/loginId")
   public String loginidexists(@RequestParam("loginId") String loginId){
        System.out.println("loginId exists in restController");
        String result= service.fetchLoginId(loginId);
        System.out.println("fetch loginId:"+loginId);
        System.out.println("result:"+result);
        if(result==null){
            return " ";
        }
        return "loginId exists";
    }

    @GetMapping("phonenumber")
    public String phoneNumberexists(@RequestParam("phoneNumber") String phoneNumber){
        System.out.println("Phone number exists in rest controller");
        String result= service.fetchPhoneNumber(phoneNumber);
        System.out.println("fetch phoneNumber:"+phoneNumber);
        System.out.println("result:"+result);
        if(result==null){
            return "phone number not exists ";
        }
        return "phone number exists";
    }

}
