package com.xworkz.soap.repository;

import com.xworkz.soap.dto.SoapDto;
import com.xworkz.soap.entity.SoapEntity;

import java.util.List;

public interface SoapRepository {
    boolean save(SoapEntity soapEntity);
    List<SoapEntity> findAllEntity();
    SoapEntity findById(Integer id);
    boolean updateById(SoapEntity soapEntity);
    boolean deleteById(Integer id);
}
