package com.xworkz.application.repository;

import com.xworkz.application.entity.ApplicationEntity;

public interface ApplicationRepository {
   public void saveApplication(ApplicationEntity applicationEntity);
    ApplicationEntity readById(int id);
   public void updateCompanyById(int id, String newCompanyName);
   public void deleteById(int id);

}
