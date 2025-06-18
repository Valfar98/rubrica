package com.corso.rubrica.model;

public enum Ruolo {
    ADMIN("ADMIN"),
    USER("USER");

    private final String valore;

    Ruolo(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }
    
    
}