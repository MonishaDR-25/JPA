package com.xworkz.DairyManagement.restController;

import com.xworkz.DairyManagement.service.AgentService;
import com.xworkz.DairyManagement.entity.AgentEntity;

import com.xworkz.DairyManagement.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/")
public class ProductCollectionRestController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private ProductService productService;

//    @GetMapping("/productCollection")
//    public ModelAndView showProductCollectionPage() {
//        ModelAndView modelAndView = new ModelAndView("productCollection");
//        List<ProductDto> products = productService.findAllProducts();
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

    @GetMapping("/productCollection/getAgentByPhone")
    public Map<String, Object> getAgentByPhone(@RequestParam String phoneNumber) {
        AgentEntity agentDto = agentService.findByPhone(phoneNumber);

        System.out.println("agentDto: " + agentDto);
        
        Map<String, Object> response = new HashMap<>();
        if (agentDto != null) {
            response.put("firstName", agentDto.getFirstName());
            response.put("lastName", agentDto.getLastName() != null ? agentDto.getLastName() : "");
            response.put("email", agentDto.getEmail() != null ? agentDto.getEmail() : "");
        }
        return response;
    }

}
