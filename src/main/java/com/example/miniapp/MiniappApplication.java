package com.example.miniapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class MiniappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniappApplication.class, args);
	}

}
