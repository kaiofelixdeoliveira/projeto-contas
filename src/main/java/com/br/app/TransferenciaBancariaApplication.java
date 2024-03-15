package com.br.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@EnableCaching
public class TransferenciaBancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferenciaBancariaApplication.class, args);
	}

	
}
