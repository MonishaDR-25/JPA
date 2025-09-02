package com.xworkz.soap.repository;

import com.xworkz.soap.entity.SoapEntity;

import java.util.List;

public interface SoapRepository {
    boolean save(SoapEntity soapEntity);
    String fetchSoapName(String name);
    List<SoapEntity> findAllEntity();
}
