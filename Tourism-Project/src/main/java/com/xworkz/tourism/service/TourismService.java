package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDto;

import java.util.List;

public interface TourismService {

    boolean validateAndSave(TourismDto tourismDto);
    List<TourismDto> getAllEntity();
}
