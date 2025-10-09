package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.ProductDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts(int page, int size);

    void saveProduct(ProductDto productDto,String adminName);

    ProductDto findById(Integer id);

    boolean updateProduct(ProductDto productDto);

    boolean deleteProduct(Integer id);

    long getProductCount();

    List<ProductDto> searchProducts(String trim, int page, int size);

    long getProductSearchCount(String trim);
}
