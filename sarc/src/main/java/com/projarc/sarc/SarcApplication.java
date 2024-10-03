package com.projarc.sarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.projarc.sarc" })
public class SarcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SarcApplication.class, args);
	}

}
