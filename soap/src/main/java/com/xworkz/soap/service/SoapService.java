package com.xworkz.soap.service;

import com.xworkz.soap.dto.SoapDto;

import java.util.List;

public interface SoapService {
    boolean validateAndSave(SoapDto soapDto);
    String fetchSoapName(String name);
    List<SoapDto> findAllEntity();
}
