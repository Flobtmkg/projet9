package com.projet9.microserviceusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceusersApplication.class, args);
	}

}

