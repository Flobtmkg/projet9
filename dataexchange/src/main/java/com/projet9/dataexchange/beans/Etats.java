package com.projet9.dataexchange.beans;

public enum Etats {
    NONPAYEE("NP"),
    ANNULEEAVANTPAIEMENT("AAVP"),
    PAYEE("P"),
    ANNUlEEAPRESPAIEMENT("AAPP");

    private String code;

    Etats(String code){
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
