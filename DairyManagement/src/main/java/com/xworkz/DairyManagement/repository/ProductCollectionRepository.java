package com.xworkz.DairyManagement.repository;

import com.xworkz.DairyManagement.entity.ProductCollectionAuditEntity;
import com.xworkz.DairyManagement.entity.ProductCollectionEntity;

import java.time.LocalDate;
import java.util.List;

public interface ProductCollectionRepository {
    void save(ProductCollectionEntity entity);

    void saveAudit(ProductCollectionAuditEntity audit);

    List<ProductCollectionEntity> getAllProductsByCollectionDate();

    List<ProductCollectionEntity> findByCollectedDate(LocalDate d);

    ProductCollectionEntity findByIdWithRelations(Integer id);

    List<ProductCollectionEntity> findBetweenDates(LocalDate start, LocalDate end);
}
