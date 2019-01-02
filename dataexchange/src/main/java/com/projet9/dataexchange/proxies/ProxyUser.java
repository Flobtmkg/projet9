package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "microserviceusers", url = "localhost:8084")
public interface ProxyUser {

}
