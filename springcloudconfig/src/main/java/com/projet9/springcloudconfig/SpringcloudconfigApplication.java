package com.projet9.springcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableConfigServer
@Configuration
public class SpringcloudconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudconfigApplication.class, args);
	}

}

