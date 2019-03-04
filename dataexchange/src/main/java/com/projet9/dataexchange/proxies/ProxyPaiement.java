package com.projet9.dataexchange.proxies;

import com.projet9.dataexchange.beans.Reservation;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "zuulapigateway")
@RibbonClient(name = "microservicepaiements")
public interface ProxyPaiement {

    @PostMapping(path = "microservicepaiements/api/Payments")
    boolean doPayement(@RequestBody Reservation reservation);

}
