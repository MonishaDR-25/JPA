package com.xworkz.common.restController;

import com.xworkz.common.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class LoginRestController {
    @Autowired
    private RegisterService service;

    public LoginRestController() {
        System.out.println("Running LoginRestController");
    }

    @GetMapping("/loginEmailCheck")
    public String checkEmail(@RequestParam("email") String email) {
        System.out.println("checkEmail in rest controller");
        String existingEmail = service.checkExistingEmail(email);
        System.out.println("Existing email: " + existingEmail);
        if (!email.equalsIgnoreCase(existingEmail))
            return "";
        return "Correct email";
    }
}
