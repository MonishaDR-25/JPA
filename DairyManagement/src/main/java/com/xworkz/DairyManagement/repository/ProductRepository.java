package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    public List<ProductEntity> getAllProducts(int page, int size);

    void save(ProductEntity productEntity);

    ProductEntity findById(Integer id);

    void update(ProductEntity productEntity);

    void delete(Integer id);

    long getProductCount();

    List<ProductEntity> searchProducts(String trim, int start, int size);

    long getProductSearchCount(String trim);
}
