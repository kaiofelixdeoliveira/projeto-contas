package com.br.contas.api.projetocontas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransferenciaBancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferenciaBancariaApplication.class, args);
	}

}
