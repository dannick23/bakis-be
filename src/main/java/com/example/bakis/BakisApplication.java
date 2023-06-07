package com.example.bakis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BakisApplication {
	public static void main(String[] args) {
		SpringApplication.run(BakisApplication.class, args);
	}

}
