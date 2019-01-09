package com.projet9.dataexchange.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "microserviceusers", url = "localhost:8084")
public interface ProxyUser {

}
