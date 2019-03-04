package com.projet9.microservicepaiements.controller;


import com.paypal.api.payments.Payment;
import com.projet9.dataexchange.beans.Reservation;
import com.projet9.microservicepaiements.Managers.PaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentManager paymentManager;

    @PostMapping(path = "/api/Payments", produces = "application/json")
    public boolean doPayement(@RequestBody Reservation reservation){
        Payment pay = paymentManager.doPay(paymentManager.configPayment(reservation));
        if(pay != null && pay.getFailureReason()==null){
            return true;
        }else{
            return false;
        }
    }
}
