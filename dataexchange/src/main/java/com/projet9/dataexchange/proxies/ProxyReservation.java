package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicereservations", url = "localhost:8083")
public class ProxyReservation {

}
