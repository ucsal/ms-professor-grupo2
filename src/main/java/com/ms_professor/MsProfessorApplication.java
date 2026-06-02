package com.ms_professor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsProfessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProfessorApplication.class, args);
	}

}
