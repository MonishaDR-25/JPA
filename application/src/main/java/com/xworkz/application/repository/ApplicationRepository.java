package com.xworkz.application.repository;

import com.xworkz.application.entity.ApplicationEntity;

import java.util.Collection;
import java.util.List;

public interface ApplicationRepository {
   public void saveApplication(ApplicationEntity applicationEntity);
    ApplicationEntity readById(int id);
   public void updateCompanyById(int id, String newCompanyName);
   public void deleteById(int id);
    ApplicationEntity getApplicationByName(String name);
    ApplicationEntity getApplicationBySize(String size);
    ApplicationEntity getApplicationByCompany(String company);
    List<ApplicationEntity> getApplication();
    List<String> getApplicationName();
    List<Integer> getApplicationNoOfUsers();
    List<Object> getApplicationDate();
    List<Object[]> getApplicationNameAndNoOfUsers();


}
