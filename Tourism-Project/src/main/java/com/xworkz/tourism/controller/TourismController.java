package com.xworkz.tourism.controller;

import com.xworkz.tourism.dto.TourismDto;
import com.xworkz.tourism.service.TourismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TourismController {
    @Autowired
    private TourismService tourismService;

    public TourismController(){
        System.out.println("Running Tourism Controller");
    }

    @RequestMapping("/redirectToTourism")
    public String getTourismForm(){
        System.out.println("Get tourism form");
        return "Tourism";
    }

    @PostMapping("/tourism")
    public String getTourism(TourismDto dto, Model model)
    {
        System.out.println("getTourism method");
        System.out.println("Controller data: "+dto);
        if(tourismService.validateAndSave(dto)) {
            model.addAttribute("message", "Submitted successfully");
        }else {
            model.addAttribute("message", "Invalid details");
            model.addAttribute("dto",dto);
        }
//        DBConnection.closeResources();
//        DBConnection.closeResources();
        return "Tourism";
    }

    @GetMapping("/getAllEntity")
    public String getAllEntity(Model model)
    {
        System.out.println("getAllEntity method in controller");
        List<TourismDto> list=tourismService.getAllEntity();
        list.forEach(System.out::println);
        model.addAttribute("listOfDto",list);
        return "ListOfTourism";
    }

}
