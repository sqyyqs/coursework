package com.sqy.delivery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Slf4j
public class NewcourseworkApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(NewcourseworkApplication.class, args);
        } catch (Exception e) {
            log.error("Error starting the application.", e);
        }
    }

}
