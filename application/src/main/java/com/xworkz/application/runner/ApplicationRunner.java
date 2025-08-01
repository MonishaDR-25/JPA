package com.xworkz.application.runner;

import com.xworkz.application.entity.ApplicationEntity;
import com.xworkz.application.repository.ApplicationRepository;
import com.xworkz.application.repository.ApplicationRepositoryImpl;
import com.xworkz.application.service.ApplicationService;
import com.xworkz.application.service.ApplicationServiceImpl;

import java.time.LocalDate;

public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationRepository applicationRepository=new ApplicationRepositoryImpl();
        // Save
        System.out.println("Save operation");
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationName("Zomato");
        applicationEntity.setApplicationSize("Small");
        applicationEntity.setCompany("Zomato Ltd");
        applicationEntity.setNoOfUsers(7000);
        applicationEntity.setRatings(4.6F);
        applicationEntity.setLaunchDate(LocalDate.of(2023, 6, 15));
        applicationRepository.saveApplication(applicationEntity);

        // Read
        System.out.println("Read operation");
        ApplicationEntity read = applicationRepository.readById(1);
        System.out.println(read);

        // Update
        System.out.println("Update operation");
        applicationRepository.updateCompanyById(1, "Zomato India Pvt Ltd");
        System.out.println(applicationEntity);


        // Delete
        System.out.println("Delete operation");
        applicationRepository.deleteById(1);
        System.out.println(applicationEntity);
    }
}
