package com.projet9.dataexchange.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microservicepaiements")
@RibbonClient(name = "microservicepaiements")
public interface ProxyPaiement {

}
