package com.xworkz.tourism.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.xworkz.tourism")
public class TourismConfiguration {

    public TourismConfiguration(){
        System.out.println("Running Tourism Configuration");
    }

    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;

    }


}
