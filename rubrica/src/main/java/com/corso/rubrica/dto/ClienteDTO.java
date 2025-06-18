package com.corso.rubrica.dto;

public class ClienteDTO {
    private Integer id;
    private String username;
    private String ruolo;

    public ClienteDTO(Integer id, String username, String ruolo) {
        this.id = id;
        this.username = username;
        this.ruolo = ruolo;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
