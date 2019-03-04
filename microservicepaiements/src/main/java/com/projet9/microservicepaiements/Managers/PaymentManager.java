package com.projet9.microservicepaiements.Managers;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.projet9.dataexchange.beans.Reservation;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentManager {

    String clientId = "AYSq3RDGsmBLJE-otTkBtM-jBRd1TCQwFf9RGfwddNXWz0uFU9ztymylOhRS";
    String clientSecret = "EGnHDxD_qRPdaLdZz8iCr8N7_MzF-YHPTkjs6NKYQvQSBngp4PTTVWkPZRbL";

    public Payment configPayment(Reservation reservation){

        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal("1.00");
        //amount.setTotal(new DecimalFormat("#.##").format(reservation.getAventure().getPrix().floatValue()));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setEmail(reservation.getUser().getEmail());
        payerInfo.setFirstName(reservation.getUser().getPrenom());
        payerInfo.setLastName(reservation.getUser().getNom());
        payerInfo.setBirthDate(reservation.getUser().getDateNaissance().toString());

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payer.setPayerInfo(payerInfo);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://example.com/cancel");
        redirectUrls.setReturnUrl("https://example.com/return");
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    public Payment doPay(Payment configuredPayment){
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = configuredPayment.create(apiContext);
            return createdPayment;
        } catch (PayPalRESTException e) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }


}
