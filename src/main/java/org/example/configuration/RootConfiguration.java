package org.example.configuration;

import org.example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

@Configuration
@ComponentScan("org.example")
public class RootConfiguration {

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public Map<String,String> map(){
        return new HashMap<>();
    }

    @Bean
    public List<User> userList(){
        return new ArrayList<>();
    }

    @Bean
    public List<String> historyList(){
        return new ArrayList<>();
    }
}
