package com.corso.rubrica.dto;

public class AuthResponse {
    private String token;
    private ClienteDTO cliente;

    public AuthResponse(String token, ClienteDTO cliente) {
        this.token = token;
        this.cliente = cliente;
    }

    public String getToken() {
        return token;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
