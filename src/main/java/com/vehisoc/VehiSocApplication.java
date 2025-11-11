package com.vehisoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehiSocApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiSocApplication.class, args);
	}

}
