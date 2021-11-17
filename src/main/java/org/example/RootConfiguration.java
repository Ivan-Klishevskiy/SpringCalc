package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan("org.example")
public class RootConfiguration {

    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
