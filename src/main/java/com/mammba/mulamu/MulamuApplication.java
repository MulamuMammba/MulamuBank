package com.mammba.mulamu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import static com.mammba.mulamu.controller.ConsoleAccountManagers.App;

@SpringBootApplication
public class MulamuApplication {

	public static void main(String[] args) {
//		App();
		SpringApplication.run(MulamuApplication.class, args);
	}



}

