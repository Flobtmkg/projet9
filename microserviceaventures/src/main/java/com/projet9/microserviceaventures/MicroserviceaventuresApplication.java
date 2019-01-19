package com.projet9.microserviceaventures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceaventuresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceaventuresApplication.class, args);
	}

}

