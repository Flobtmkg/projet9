package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicepaiement", url = "localhost:8082")
public class ProxyPaiement {

}
