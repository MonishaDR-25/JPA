package com.xworkz.application.service;

import com.xworkz.application.entity.ApplicationEntity;

public class ApplicationServiceImpl implements ApplicationService{

    @Override
    public boolean validate(ApplicationEntity applicationEntity) {
        System.out.println("Running validate in ApplicationServiceImpl");
        if(applicationEntity!=null){
            if(applicationEntity.getApplicationId()!=null){
                System.out.println("Id is valid");
            }else{
                System.out.println("Id is not valid");
            }
            if(applicationEntity.getApplicationName()!=null){
                System.out.println("Name is valid");
            }else{
                System.out.println("Name is not valid");
            }
            if(applicationEntity.getApplicationSize()!=null){
                System.out.println("Size is valid");
            }else{
                System.out.println("Size is not valid");
            }if(applicationEntity.getCompany()!=null){
                System.out.println("company is valid");
            }else{
                System.out.println("company is not valid");
            }
            if(applicationEntity.getNoOfUsers()!=null){
                System.out.println("No of users is valid");
            }else{
                System.out.println("No of users is not valid");
            }
            if(applicationEntity.getRatings()!=null){
                System.out.println("Ratings is valid");
            }else{
                System.out.println("Ratings is not valid");
            }if(applicationEntity.getLaunchDate()!=null){
                System.out.println("Date is valid");
            }else{
                System.out.println("Date is not valid");
            }
        }
        System.out.println("Application data is good");
        return true;
    }
}
