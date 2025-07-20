package com.nikky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoflowApplication {

    public static void main(String[] args) {
        System.out.println("Starting Autoflow Spring Boot App...");
        SpringApplication.run(AutoflowApplication.class, args);
    }
}
