package com.xworkz.common.restController;

import com.xworkz.common.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RegisterRestController {
    @Autowired
    private RegisterService service;

    public RegisterRestController()
    {
        System.out.println("RegisterRestController constructor");
    }


    @GetMapping("/emailCheck")
    public String checkEmail(@RequestParam("email")String email)
    {
        System.out.println("checkEmail method in rest controller");
        String existingEmail=service.checkExistingEmail(email);
        System.out.println("Existing email: "+existingEmail);
        if(existingEmail==null)
            return "";
        return "Email already exists";
    }

    @GetMapping("/phoneNumberCheck")
    public String checkPhoneNumber(@RequestParam("phoneNumber")String phoneNumber)
    {
        System.out.println("checkPhoneNumber method in rest controller");
        String existingNumber=service.checkExistingPhoneNumber(phoneNumber);
        System.out.println("Existing email: "+existingNumber);
        if(existingNumber==null)
            return "";
        return "PhoneNumber already exists";
    }
}
