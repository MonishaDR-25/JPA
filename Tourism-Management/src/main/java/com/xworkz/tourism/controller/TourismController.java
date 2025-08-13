package com.xworkz.tourism.controller;

import com.xworkz.tourism.dto.TourismDto;
import com.xworkz.tourism.service.TourismService;
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
public class TourismController {
    @Autowired
    private TourismService service;

    public TourismController() {
        System.out.println("App Controller ...");
    }

    @GetMapping("redirectToTourism")
    public String setIndex() {
        System.out.println("Index...");
        return "register";
    }

    @PostMapping("register")
    public String getRegister(TourismDto dto, Model model) {
        System.out.println("DTO Registered");
        System.out.println(dto);
        if (service.validation(dto)) {
            model.addAttribute("message", "Submitted");
        } else {
            model.addAttribute("message", "Not Submitted");
            model.addAttribute("dto", dto);
        }
        return "register";
    }

    @GetMapping("getAllPackages")
    public String getAllPackages(Model model) {
        System.out.println("getAllPackages Method...");
        List<TourismDto> list = service.getAllPackage();
        model.addAttribute("ListOfDto", list);
        list.forEach(System.out::println);
        return "listOfDto";
    }

    @GetMapping("returnToIndex")
    public String redirectToIndex() {
        System.out.println("Calling Index");
        return "index";
    }

    @GetMapping("view")
    public String getPackageUrl(@RequestParam("id") String id, Model model) {
        TourismDto dto = service.fetchDataByID(Integer.parseInt(id));
        model.addAttribute("ref", dto);
        return "view";
    }
}
