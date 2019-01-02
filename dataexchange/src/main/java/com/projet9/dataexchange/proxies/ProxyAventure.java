package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microserviceaventures", url = "localhost:8081")
public interface ProxyAventure {

}
