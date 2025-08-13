package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDto;

import java.util.List;

public interface TourismService {
    boolean validation(TourismDto dto);
    List<TourismDto> getAllPackage();
    TourismDto fetchDataByID(Integer id);
}
