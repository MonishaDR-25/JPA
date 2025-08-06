package com.xworkz.application.runner;

import com.xworkz.application.entity.ApplicationEntity;
import com.xworkz.application.repository.ApplicationRepoImpl;
import com.xworkz.application.repository.ApplicationRepository;

import java.time.LocalDate;
import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationRepository applicationRepository=new ApplicationRepoImpl();
        // Save
       // System.out.println("Save operation");
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationName("FaceBook");
        applicationEntity.setApplicationSize("Big");
        applicationEntity.setCompany("Meta");
        applicationEntity.setNoOfUsers(9000);
        applicationEntity.setRatings(5.6F);
        applicationEntity.setLaunchDate(LocalDate.of(2023, 6, 15));
//        ApplicationEntity read = applicationRepository.getApplicationByName("Amazon");
//       System.out.println(read);
//      ApplicationEntity sizeEntity=applicationRepository.getApplicationBySize("Large");
//       System.out.println(sizeEntity);
//        List<ApplicationEntity> ref=applicationRepository.getApplication();
//        System.out.println(ref);

        List<String> ref=applicationRepository.getApplicationName();
        ref.forEach(System.out::println);

        List<Integer> ref0=applicationRepository.getApplicationNoOfUsers();
        System.out.println(ref0);

        List<Object> ref1=applicationRepository.getApplicationDate();
        ref1.forEach(System.out::println);
//
        List<Object[]> ref2=applicationRepository.getApplicationNameAndNoOfUsers();
        for (Object[] data:ref2){
            System.out.println("Application Name:"+data[0]+"No Of Users:"+data[1]);
        }
        ref2.stream().map(r->"Name:"+r[0]+"NoOfUsers:"+r[1]).forEach(System.out::println);
        //applicationRepository.saveApplication(applicationEntity);

//        // Read
//        System.out.println("Read operation");
//        ApplicationEntity read = applicationRepository.readById(1);
//        System.out.println(read);
//
//        // Update
//        System.out.println("Update operation");
//        applicationRepository.updateCompanyById(1, "Zomato India Pvt Ltd");
//        System.out.println(applicationEntity);
//
//
//        // Delete
//        System.out.println("Delete operation");
//        applicationRepository.deleteById(1);
//        System.out.println(applicationEntity);
    }
}
