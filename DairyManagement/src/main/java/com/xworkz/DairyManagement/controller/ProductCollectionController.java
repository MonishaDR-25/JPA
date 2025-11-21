package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.dto.ProductCollectionAgentDto;
import com.xworkz.DairyManagement.dto.ProductDto;
import com.xworkz.DairyManagement.service.ProductCollectionService;
import com.xworkz.DairyManagement.dto.ProductCollectionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class ProductCollectionController {

    @Autowired
    private ProductCollectionService productCollectionService;

    @GetMapping("productCollection")
    public String productCollection(HttpSession session, Model model) {
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {


            return "redirect:/adminLogin";
        }

        List<ProductDto> products = productCollectionService.getAllProductsByTypesOfMilk();



        model.addAttribute("products",products);

//        log.info("ProductCollectionController is working");
//        log.info("products: {}",products);


        return "productCollection";
    }

    @PostMapping("/saveProductCollection")
    public String saveProductCollection(@ModelAttribute ProductCollectionDto productCollectionDto ,
                                        Model model,HttpSession session){
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLogin";
        }

        productCollectionService.saveProductCollection(productCollectionDto,loggedInAdmin);
        model.addAttribute("successMessage", "Product Collection saved successfully!");
        return "redirect:/productCollection";
    }

    @GetMapping("/productCollectionList")
    public String productCollectionList(@RequestParam(required = false) String date, HttpSession session, Model model) {
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLogin";
        }
        List<ProductCollectionDto> products = productCollectionService.getAllProductsByCollectionDate(date);
        model.addAttribute("products",products);
        return "productCollectionList";
    }

    @GetMapping(value = "/viewCollectionDetails",produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductCollectionAgentDto> viewCollectionDetails(@RequestParam Integer id){
//        return ResponseEntity.ok(productCollectionService.getDetailsDTO(id));
        try {
            return ResponseEntity.ok(productCollectionService.getDetailsDTO(id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        }





}
