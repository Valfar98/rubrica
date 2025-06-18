package com.corso.rubrica.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private Ruolo ruolo;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Contatto> contatti;

	public Cliente() {
		super();
	}

	public Cliente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	

	public Set<Contatto> getContatti() {
		return contatti;
	}

	public void setContatti(Set<Contatto> contatti) {
		this.contatti = contatti;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
