package com.projet9.dataexchange.beans;

public enum Etats {
    NONPAYEE("Non-payée"),
    ANNULEEAVANTPAIEMENT("Annulée avant paiement"),
    PAYEE("Payée"),
    ANNUlEEAPRESPAIEMENT("Annulée après paiement");

    private String name = "";

    Etats(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
