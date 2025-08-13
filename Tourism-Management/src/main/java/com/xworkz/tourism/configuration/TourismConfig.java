package com.xworkz.tourism.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.xworkz.tourism")
@EnableWebMvc
public class TourismConfig {
    public TourismConfig(){
        System.out.println("Running Tourism Configuration");
    }

    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
