package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDto;
import com.xworkz.tourism.entity.TourismEntity;
import com.xworkz.tourism.repository.TourismRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TourismServiceImpl implements TourismService{

    @Autowired
    private TourismRepository repo;

    public TourismServiceImpl() {
        System.out.println("Validation......");
    }

    @Override
    public boolean validation(TourismDto dto) {
        if (dto != null) {
            System.out.println("DTO is Valid");
        } else {
            System.out.println("DTO is Not Valid");
            return false;
        }

        if (dto.getPackageName() != null) {
            System.out.println("Package Name is Valid");
        } else {
            System.out.println("Package Name is Not Valid");
            return false;
        }

        if (dto.getDays() != null && dto.getDays() > 0) {
            System.out.println("Days Are Valid");
        } else {
            System.out.println("Days Are Not Valid");
            return false;
        }

        if (dto.getDestination() != null) {
            System.out.println("Destination Is Valid");
        } else {
            System.out.println("Destination is Not Valid");
            return false;
        }

        if (dto.getPackagePrice() != null && dto.getPackagePrice() > 1000) {
            System.out.println("The Price is Valid");
        } else {
            System.out.println("Price is Not Valid");
            return false;
        }

        if (dto.getPersonCount() != null && dto.getPersonCount() > 0) {
            System.out.println("Person Are Valid");
        } else {
            System.out.println("Persons are not valid");
            return false;
        }

        TourismEntity entity = new TourismEntity();
        entity.setPackageName(dto.getPackageName());
        entity.setPersonCount(dto.getPersonCount());
        entity.setDestination(dto.getDestination());
        entity.setPackagePrice(dto.getPackagePrice());
        entity.setDays(dto.getDays());
        return repo.save(entity);
    }

    @Override
    public List<TourismDto> getAllPackage() {
        System.out.println("Invoking getAllPackage...");
        List<TourismEntity> entities = repo.getAllPackage();
        return entities.stream().map(entity -> {
            TourismDto dto = new TourismDto();
            dto.setPackageId(entity.getPackageId());
            dto.setPackageName(entity.getPackageName());
            dto.setDestination(entity.getDestination());
            dto.setDays(entity.getDays());
            dto.setPackagePrice(entity.getPackagePrice());
            dto.setPersonCount(entity.getPersonCount());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public TourismDto fetchDataByID(Integer id) {
        TourismEntity entity = repo.fetchDataByID(id);
        TourismDto dto = new TourismDto();
        dto.setPackageId(entity.getPackageId());
        dto.setPackageName(entity.getPackageName());
        dto.setDestination(entity.getDestination());
        dto.setDays(entity.getDays());
        dto.setPackagePrice(entity.getPackagePrice());
        dto.setPersonCount(entity.getPersonCount());
        return dto;
    }
}
