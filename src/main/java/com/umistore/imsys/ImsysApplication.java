package com.umistore.imsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ImsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsysApplication.class, args);
	}

}
