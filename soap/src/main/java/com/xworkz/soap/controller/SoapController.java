package com.xworkz.soap.controller;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.service.SoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class SoapController {

    @Autowired
    private SoapService soapService;

    public SoapController(){
        System.out.println("Running SoapController");
    }

    @GetMapping("redirectToSoap")
    public String setIndex(){
        System.out.println("Created Index..");
        return "Soap";
    }

    @GetMapping("redirectToView")
    public String getAllData(Model model){
        System.out.println("Get all Data");
        List<SoapDto> soapDto=soapService.findAllEntity();
        model.addAttribute("list",soapDto);
        return "view";
    }

    @PostMapping("soap")
    public String getSoap(SoapDto soapDto, Model model){
        System.out.println("SoapDto registered");
        System.out.println(soapDto);
        if(soapService.validateAndSave(soapDto)){
            model.addAttribute("message","submitted");
        }
        else{
            model.addAttribute("message","Not Submitted");
            model.addAttribute("soapDto",soapDto);
        }
        return "Soap";
    }

}