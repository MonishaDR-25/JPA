package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDto;
import com.xworkz.tourism.entity.TourismEntity;
import com.xworkz.tourism.repository.TourismRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourismServiceImpl implements TourismService{

    @Autowired
    private TourismRepository tourismRepository;

    public TourismServiceImpl(){
        System.out.println("Running TourismServiceImpl");
    }

    @Override
    public boolean validateAndSave(TourismDto tourismDto) {
        System.out.println("Validate tourism details...");
        if(tourismDto!=null){
            System.out.println("Dto is not null");
        }else {
            System.out.println("Dto is null");
            return false;
        }
        if(tourismDto.getPackageName()!=null && !tourismDto.getPackageName().isEmpty()){
            System.out.println("Name is valid");
        }else{
            System.out.println("Name is not valid");
            return false;
        }
        if(tourismDto.getDestination()!=null && tourismDto.getDestination().length()>=3){
            System.out.println("Destination is valid");
        }else{
            System.out.println("Destination is not valis");
            return false;
        }
        if(tourismDto.getDays()!=null && tourismDto.getDays()>=2){
            System.out.println("Days are valid");
        }
        else {
            System.out.println("Days are not valid");
            return false;
        }
        if(tourismDto.getPackagePrice()!=null && tourismDto.getPackagePrice()>=500){
            System.out.println("price is valid");
        }else{
            System.out.println("price is not valid");
            return false;
        }
        if(tourismDto.getPersonCount()!=null && tourismDto.getPersonCount()>0){
            System.out.println("person count is valid");
        }else{
            System.out.println("Person count is not valid");
            return false;
        }
        System.out.println("All data is good...");
        return true;

    }

    @Override
    public List<TourismDto> getAllEntity() {
        System.out.println("getAllEntity method in service");
        List<TourismEntity> listOfTourismEntity=tourismRepository.getAllEntity();
        List<TourismDto> listOfTourismDto=listOfTourismEntity.stream()
                .map(entity -> {
                    TourismDto dto = new TourismDto();
                    dto.setPackageId(entity.getPackageId());
                    dto.setPackagePrice(entity.getPackagePrice());
                    dto.setDays(entity.getDays());
                    dto.setDestination(entity.getDestination());
                    dto.setPackageName(entity.getPackageName());
                    dto.setPersonCount(entity.getPersonCount());
                    return dto;
                })
                .collect(Collectors.toList());

        return listOfTourismDto;
    }
}
