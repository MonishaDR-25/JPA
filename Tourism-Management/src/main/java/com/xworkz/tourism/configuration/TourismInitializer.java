package com.xworkz.tourism.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class TourismInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public TourismInitializer(){
        System.out.println("created Tourism Initializer");
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("Running getRootConfigClasses");
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("Running getServletConfigClasses");
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("Running getServletMappings");
        return new String[0];
    }
}
