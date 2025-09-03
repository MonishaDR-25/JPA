package com.xworkz.soap.service;

import com.xworkz.soap.dto.SoapDto;

import java.util.List;

public interface SoapService {
    boolean validateAndSave(SoapDto soapDto);
    List<SoapDto> findAllEntity();
    SoapDto findById(Integer id);
     String updateById(SoapDto soapDto);
     String deleById(Integer id);
}
