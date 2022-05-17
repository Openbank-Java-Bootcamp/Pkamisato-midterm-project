package com.ironhack.openbank_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static java.math.RoundingMode.UP;

@SpringBootApplication
public class OpenbankProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenbankProjectApplication.class, args);
		LocalDate dateTime = LocalDate.now();
		System.out.println(dateTime);
		dateTime=dateTime.plusYears(1);
		System.out.println(dateTime);


	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}




}

