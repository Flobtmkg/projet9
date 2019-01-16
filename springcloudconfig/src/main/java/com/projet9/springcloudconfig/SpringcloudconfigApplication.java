package com.projet9.springcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.core.env.Environment;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableConfigServer
@Configuration
public class SpringcloudconfigApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringcloudconfigApplication.class, args);
	}

	/*@Bean
	public ConfigServicePropertySourceLocator configServicePropertySourceLocator() {
		ConfigClientProperties clientProperties = configClientProperties();
		ConfigServicePropertySourceLocator configServicePropertySourceLocator =  new ConfigServicePropertySourceLocator(clientProperties);
		configServicePropertySourceLocator.setRestTemplate(customRestTemplate(clientProperties));
		return configServicePropertySourceLocator;
	}*/

}

