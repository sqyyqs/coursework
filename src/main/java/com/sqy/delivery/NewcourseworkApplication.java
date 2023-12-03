package com.sqy.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NewcourseworkApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(NewcourseworkApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
