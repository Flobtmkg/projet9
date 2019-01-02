package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicepaiements", url = "localhost:8082")
public interface ProxyPaiement {

}
