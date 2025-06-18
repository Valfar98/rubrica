package com.corso.rubrica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.corso.rubrica.model.Cliente;
import com.corso.rubrica.model.Ruolo;
import com.corso.rubrica.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository rep;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public void init() {
		Cliente clienteAdmin = new Cliente("admin", passwordEncoder.encode("admin"));
		clienteAdmin.setRuolo(Ruolo.ADMIN);
		rep.save(clienteAdmin);

		Cliente clienteUser = new Cliente("user", passwordEncoder.encode("user"));
		clienteUser.setRuolo(Ruolo.USER);
		rep.save(clienteUser);
	}

	public Cliente registrazione(Cliente c) {
	    if (rep.findByUsername(c.getUsername()) != null) {
	        throw new RuntimeException("Username già in uso. Scegli un altro username.");
	    }
	    c.setRuolo(Ruolo.USER);
	    c.setPassword(passwordEncoder.encode(c.getPassword()));
	    return rep.save(c);
	}

	public List<Cliente> getClienti() {
		return rep.findAll();
	}

	public Cliente modificaCliente(Cliente c) {
	    Cliente cliente = rep.findById(c.getId()).orElse(null);
	    if (cliente == null) {
	        throw new RuntimeException("Cliente non trovato");
	    }

	    if (c.getUsername() != null && !c.getUsername().trim().isEmpty()) {
	        // Potresti voler aggiungere un controllo per username duplicato anche qui
	        cliente.setUsername(c.getUsername());
	    }
	    if (c.getPassword() != null && !c.getPassword().trim().isEmpty()) {
	        // **IMPORTANTE: HASH LA NUOVA PASSWORD PRIMA DI SALVARLA**
	        cliente.setPassword(passwordEncoder.encode(c.getPassword()));
	    }

	    return rep.save(cliente);
	}
	
	public Cliente getUtenteAutenticato() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
	        throw new RuntimeException("Nessun utente autenticato");
	    }

	    String username = auth.getName();
	    Cliente u= rep.findByUsername(username);
	    if(u==null) {	
	    	throw new RuntimeException("Utente non trovato nel DB");
	    }
	    return u;
	}
	
	public Cliente findByUsername(String username) {
		return rep.findByUsername(username);
	}

}
