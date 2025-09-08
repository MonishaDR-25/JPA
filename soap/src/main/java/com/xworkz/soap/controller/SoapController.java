package com.xworkz.soap.controller;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.service.SoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String saveSoap(){
        System.out.println("Created Soap Index..");
        return "Soap";
    }

    @GetMapping("redirectToIndex")
    public String setIndex(){
        System.out.println("Created Index..");
        return "index";
    }


    @PostMapping("soap")
    public String getSoap(SoapDto soapDto, Model model){
        System.out.println("SoapDto registered");
        System.out.println(soapDto);
        if(soapService.validateAndSave(soapDto)){
            model.addAttribute("message","submitted");
            List<SoapDto> soapDtos = soapService.findAllEntity();
            model.addAttribute("list", soapDtos);
            return "SoapList";
        }
        else{
            model.addAttribute("message","Not Submitted");
            model.addAttribute("soapDto",soapDto);
        }
        return "Soap";
    }

    @GetMapping("getAllData")
    public String getAllData(Model model){
        System.out.println("Get all Data");
        List<SoapDto> soapDto=soapService.findAllEntity();
        model.addAttribute("list",soapDto);
        return "SoapList";
    }

    @GetMapping("view")
    public String getById(@RequestParam("id")Integer id,Model model){
        System.out.println("Get By id in controller");
        SoapDto soapDto=soapService.findById(id);
        model.addAttribute("soapDto",soapDto);
        return "view";
    }

    @GetMapping("edit")
    public String viewById(@RequestParam("id")Integer id,Model model){
        System.out.println("View by id controller");
        SoapDto soapDto=soapService.findById(id);
        model.addAttribute("dto",soapDto);
        return "update";
    }

    @PostMapping("update")
    public String updateById(SoapDto soapDto,Model model){
        System.out.println("Update in controller");
        String update=soapService.updateById(soapDto);
        System.out.println(update);
        List<SoapDto> soapDtos=soapService.findAllEntity();
        model.addAttribute("List",soapDtos);
        return "SoapList";
    }


    @GetMapping("delete")
    public String deleteById(@RequestParam("id")Integer id,Model model){
        System.out.println("Delete by id in controller");
        String deleted=soapService.deleById(id);
        System.out.println(deleted);
        List<SoapDto> list=soapService.findAllEntity();
        model.addAttribute("List",list);
        return "SoapList";

    }

}