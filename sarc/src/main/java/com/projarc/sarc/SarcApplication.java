package com.projarc.sarc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projarc.sarc.service.AulaService;

@SpringBootApplication
public class SarcApplication implements CommandLineRunner {

    @Autowired
    private AulaService aulaService;

    public static void main(String[] args) {
        SpringApplication.run(SarcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        aulaService.generateWeeklyClassesForAllTurmas();
    }
}
