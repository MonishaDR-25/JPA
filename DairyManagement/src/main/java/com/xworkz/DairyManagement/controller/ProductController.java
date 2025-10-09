package com.xworkz.DairyManagement.controller;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.dto.ProductDto;
import com.xworkz.DairyManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/productDashboard")
    public String productDashboard(HttpSession session, Model model,
                                   @RequestParam (defaultValue = "1")int page,
                                   @RequestParam (defaultValue = "10")int size,
                                   @RequestParam (required = false)String search){
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }

        List<ProductDto> products;
        long totalProducts;

        if (search != null && !search.trim().isEmpty()) {
            // 🔍 Search filter applied
            products = productService.searchProducts(search.trim(), page, size);
            totalProducts = productService.getProductSearchCount(search.trim());
            model.addAttribute("search", search);
        } else {

            // 📄 Normal pagination
            products = productService.getAllProducts(page, size);
            totalProducts = productService.getProductCount();
        }

        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalRecords", totalProducts);




     //   List<ProductDto> list=productService.getAllProducts();
      //  model.addAttribute("products",list);
        return "productDashboard";
    }

    @PostMapping("/registerProduct")
    public String registerProduct(ProductDto productDto, HttpSession session, RedirectAttributes attributes){
        AdminDto loggedInAdmin = (AdminDto) session.getAttribute("loggedInAdmin");
        if (loggedInAdmin == null) {
            return "redirect:/adminLoginForm";
        }
        productService.saveProduct(productDto,loggedInAdmin.getAdminName());
        attributes.addFlashAttribute("successMessage", "Product registered successfully");

        return "redirect:/productDashboard";
    }

    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("productId") Integer id, Model model) {
        ProductDto product = productService.findById(id);
        System.out.println("Editing productId: " + id);
        System.out.println("Product: " + product);

        if (product == null) {
            return "redirect:/productDashboard";  // if no product found, redirect
        }

        model.addAttribute("product", product);
        return "editProductModal"; // JSP page for editing product
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute ProductDto productDto, RedirectAttributes redirectAttributes) {
        boolean updated = productService.updateProduct(productDto);

        if (updated) {
            redirectAttributes.addFlashAttribute("successMessage", "Product Updated Successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not Updated Successfully");
        }

        return "redirect:/productDashboard";
    }


    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Product Deleted Successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete Product");
        }

        return "redirect:/productDashboard";
    }
}
