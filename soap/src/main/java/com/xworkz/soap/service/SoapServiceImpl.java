package com.xworkz.soap.service;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.entity.SoapEntity;
import com.xworkz.soap.repository.SoapRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<SoapDto> findAllEntity() {
        List<SoapEntity> soapEntity=soapRepository.findAllEntity();
        List<SoapDto> soapDto=new ArrayList<>();
        soapDto=soapEntity.stream().map(entity->{
            SoapDto soapDto1=new SoapDto();
            BeanUtils.copyProperties(entity,soapDto1);
            return soapDto1;
        }).collect(Collectors.toList());
        soapDto.forEach(System.out::println);
        return soapDto;
    }

    @Override
    public SoapDto findById(Integer id) {
        System.out.println("Find by Id in service");
        SoapEntity soapEntity=soapRepository.findById(id);
        SoapDto soapDto=new SoapDto();
        BeanUtils.copyProperties(soapEntity,soapDto);
        return soapDto;
    }

    @Override
    public String updateById(SoapDto soapDto) {
        SoapEntity soapEntity=new SoapEntity();
        BeanUtils.copyProperties(soapDto,soapEntity);
        boolean updated= soapRepository.updateById(soapEntity);
        if(updated){
            return "UPDATED";
        }
        return "Not UPDATED";
    }

    @Override
    public String deleById(Integer id) {
        boolean deleted= soapRepository.deleteById(id);
        if(deleted){
            return "DELETED";
        }
        return "NOT DELETED";
    }
}
