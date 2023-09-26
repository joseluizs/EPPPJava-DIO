package com.br.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os seuintersmódulos foram selecionados;
 * Spring Data JPA
 * Spring Web
 * H2 DataBase
 * OpenFeign
 *
 * @author joseluizs
 *
 * */
@EnableFeignClients
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
