package com.corso.rubrica.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.corso.rubrica.controller", "com.corso.rubrica.service" } )
@EnableJpaRepositories(basePackages = "com.corso.rubrica.repository")
@EntityScan(basePackages = "com.corso.rubrica.model")
public class RubricaApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(RubricaApplication.class, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
