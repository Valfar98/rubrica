package com.corso.rubrica.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.corso.rubrica.service.ClienteService;
import com.corso.rubrica.service.ContattoService;

@SpringBootApplication(scanBasePackages = {"com.corso.rubrica.controller", "com.corso.rubrica.service","com.corso.rubrica.config","com.corso.rubrica.dto" } )
@EnableJpaRepositories(basePackages = "com.corso.rubrica.repository")
@EntityScan(basePackages = "com.corso.rubrica.model")
public class RubricaApplication {

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext context= SpringApplication.run(RubricaApplication.class, args);
			ClienteService clienteService = (ClienteService) context.getBean("clienteService");
			clienteService.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
