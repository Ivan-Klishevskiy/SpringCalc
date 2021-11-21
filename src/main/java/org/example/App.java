package org.example;


import org.example.configuration.RootConfiguration;
import org.example.util.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext ac=new AnnotationConfigApplicationContext(RootConfiguration.class);
        Application application =(Application)ac.getBean("application");
        application.run();
    }
}
