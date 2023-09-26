package com.br.luiz;

import com.br.luiz.service.ViaCepService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableFeignClients
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
