package com.xworkz.soap.service;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.entity.SoapEntity;
import com.xworkz.soap.repository.SoapRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoapServiceImpl implements SoapService{

    @Autowired
    private SoapRepository soapRepository;

    @Override
    public boolean validateAndSave(SoapDto soapDto) {
        System.out.println("Running validate and save in service:"+soapDto);
        if(soapDto==null && soapDto.getName()==null && soapDto.getBrand()==null && soapDto.getColor()==null && soapDto.getCost()==null){
            System.out.println("Soap dto is null");
            return false;
        }
        SoapEntity soapEntity=new SoapEntity();
        BeanUtils.copyProperties(soapDto,soapEntity);
        return soapRepository.save(soapEntity);
    }

    @Override
    public String fetchSoapName(String name) {
        System.out.println("Created soap name in service");
        return soapRepository.fetchSoapName(name);
    }

    @Override
    public List<SoapDto> findAllEntity() {
        List<SoapEntity> soapEntity=soapRepository.findAllEntity();
        List<SoapDto> soapDto=null;
        soapDto=soapEntity.stream().map(entity->{
            SoapDto soapDto1=new SoapDto();
            BeanUtils.copyProperties(soapEntity,soapDto1);
            return soapDto1;
        }).collect(Collectors.toList());
        soapDto.forEach(System.out::println);
        return soapDto;
    }
}
