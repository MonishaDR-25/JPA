package com.xworkz.DairyManagement.service;

import com.xworkz.DairyManagement.dto.ProductDto;
import com.xworkz.DairyManagement.entity.ProductEntity;
import com.xworkz.DairyManagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductDto> getAllProducts(int page, int size) {
        List<ProductEntity> list=repository.getAllProducts(page,size);
        List<ProductDto> dtos=new ArrayList<>();
        for (ProductEntity productEntity:list){
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(productEntity,productDto);
            dtos.add(productDto);
        }
        return dtos;
    }

    @Override
    public void saveProduct(ProductDto productDto, String adminName) {
        ProductEntity productEntity=new ProductEntity();
        BeanUtils.copyProperties(productDto,productEntity);
       productEntity.setProductName(productDto.getProductName());
       productEntity.setProductPrice(productDto.getProductPrice());
       productEntity.setCreatedBy(adminName);
       productEntity.setCreatedAt(LocalDateTime.now());
        repository.save(productEntity);
    }

    @Override
    public ProductDto findById(Integer id) {
        ProductEntity productEntity = repository.findById(id);
        if (productEntity == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
        return productDto;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        ProductEntity productEntity = repository.findById(productDto.getProductId());
        if (productEntity == null) {
            return false;
        }
        BeanUtils.copyProperties(productDto, productEntity);
        repository.update(productEntity);
        return true;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        ProductEntity productEntity = repository.findById(id);
        if (productEntity == null) {
            return false;
        }
        repository.delete(id);
        return true;
    }

    @Override
    public long getProductCount() {
        return repository.getProductCount();
    }

    @Override
    public List<ProductDto> searchProducts(String trim, int page, int size) {
        int start=(page-1)*size;

        List<ProductEntity> productEntities=repository.searchProducts(trim,start,size);
        List<ProductDto> productDtos=new ArrayList<>();
        for (ProductEntity productEntity:productEntities){
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(productEntity,productDto);
            productDtos.add(productDto);
        }


        return productDtos;
    }

    @Override
    public long getProductSearchCount(String trim) {
        return repository.getProductSearchCount(trim);
    }
}
