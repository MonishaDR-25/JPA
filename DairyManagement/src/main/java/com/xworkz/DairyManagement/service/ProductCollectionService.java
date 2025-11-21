package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.AdminDto;
import com.xworkz.DairyManagement.dto.ProductCollectionAgentDto;
import com.xworkz.DairyManagement.dto.ProductCollectionDto;
import com.xworkz.DairyManagement.dto.ProductDto;

import java.util.List;

public interface ProductCollectionService {
    List<ProductDto> getAllProductsByTypesOfMilk();

    void saveProductCollection(ProductCollectionDto productCollectionDto, AdminDto adminDto);

    List<ProductCollectionDto> getAllProductsByCollectionDate(String date);

    ProductCollectionAgentDto getDetailsDTO(Integer id);
}
