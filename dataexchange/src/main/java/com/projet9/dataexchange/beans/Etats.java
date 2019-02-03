package com.projet9.dataexchange.beans;

public enum Etats {
    NONPAYEE("Non-payé"),
    ANNULEEAVANTPAIEMENT("Annulé avant paiement"),
    PAYEE("Payé"),
    ANNUlEEAPRESPAIEMENT("Annulé après paiement");

    private String name = "";

    Etats(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
