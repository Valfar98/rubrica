package com.corso.rubrica.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.rubrica.model.Utente;
import com.corso.rubrica.repository.UtenteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtenteService {

	@Autowired
	private UtenteRepository rep;

	public Utente save(Utente u) {
		return rep.save(u);
	}

	public List<Utente> getAll(String nome, String cognome) {
		List<Utente> ris = new ArrayList<>();

		boolean hasNome = nome != null && !nome.isEmpty();
		boolean hasCognome = cognome != null && !cognome.isEmpty();

		if (hasNome && hasCognome) {
			ris = rep.findByNomeLikeAndCognomeLike("%" + nome + "%", "%" + cognome + "%");
		} else if (hasNome && !hasCognome) {
			ris = rep.findByNomeLike("%" + nome + "%");
		} else if (hasCognome && !hasNome) {
			ris = rep.findByCognomeLike("%" + cognome + "%");
		} else {
			ris = rep.findAll();
		}

		return ris;
	}

	public void deleteById(Integer id) {
		rep.deleteById(id);
	}
	

	public void deleteAll() {
		rep.deleteAll();
	}

	public Utente editUtenteById(Integer id, Utente u) {
		Utente utente = rep.findById(id).orElse(null);
		if (utente != null) {
			utente.setNome(u.getNome());
			utente.setCognome(u.getCognome());
			utente.setEmail(u.getEmail());
			utente.setTelefono(u.getTelefono());
			utente.setEta(u.getEta());
			return rep.save(utente);
		}
		throw new RuntimeException("Utente non trovato");
	}
}
