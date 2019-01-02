package com.projet9.microservicereservation;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = {"com.projet9.dataexchange.proxies","com.projet9.microservicereservation"})
@Configuration
public class SpringConfig {
}
