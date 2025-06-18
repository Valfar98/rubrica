package com.corso.rubrica.service;

import java.util.List;
import java.util.Optional; // Importa Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException; // Importa per gestire l'accesso negato

import com.corso.rubrica.model.Cliente;
import com.corso.rubrica.model.Contatto;
import com.corso.rubrica.repository.ContattoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContattoService {

	@Autowired
	private ContattoRepository rep;
	@Autowired
	private ClienteService clienteService;

	public Contatto save(Contatto u) {
		Cliente clienteLoggato = clienteService.getUtenteAutenticato();
	 	u.setCliente(clienteLoggato); // Associa il contatto all'utente autenticato
		return rep.save(u);
	}

	public List<Contatto> getAll(String nome, String cognome) {
		Cliente clienteLoggato = clienteService.getUtenteAutenticato();

		boolean hasNome = nome != null && !nome.isEmpty();
		boolean hasCognome = cognome != null && !cognome.isEmpty();

		// Filtra sempre per il cliente loggato
		if (hasNome && hasCognome) {
			return rep.findByClienteAndNomeLikeAndCognomeLike(clienteLoggato, "%" + nome + "%", "%" + cognome + "%");
		} else if (hasNome) {
			return rep.findByClienteAndNomeLike(clienteLoggato, "%" + nome + "%");
		} else if (hasCognome) {
			return rep.findByClienteAndCognomeLike(clienteLoggato, "%" + cognome + "%");
		} else {
			return rep.findByCliente(clienteLoggato); // Restituisce tutti i contatti dell'utente loggato
		}
	}

	public void deleteById(Integer id) {
		Cliente clienteLoggato = clienteService.getUtenteAutenticato();
		Optional<Contatto> contattoOpt = rep.findById(id);

		if (contattoOpt.isEmpty()) {
			throw new RuntimeException("Contatto non trovato");
		}

		Contatto contatto = contattoOpt.get();

		// Verifica che il contatto appartenga all'utente autenticato
		if (!contatto.getCliente().equals(clienteLoggato)) {
			throw new AccessDeniedException("Non hai i permessi per eliminare questo contatto.");
		}

		rep.deleteById(id);
	}

	public void deleteAll() {
		Cliente clienteLoggato = clienteService.getUtenteAutenticato();
		// Elimina tutti i contatti associati all'utente autenticato
		rep.deleteByCliente(clienteLoggato);
	}

	public Contatto editUtenteById(Integer id, Contatto u) {
		Cliente clienteLoggato = clienteService.getUtenteAutenticato();
		Optional<Contatto> contattoOpt = rep.findById(id);

		if (contattoOpt.isEmpty()) {
			throw new RuntimeException("Contatto non trovato");
		}

		Contatto contatto = contattoOpt.get();

		// Verifica che il contatto appartenga all'utente autenticato
		if (!contatto.getCliente().equals(clienteLoggato)) {
			throw new AccessDeniedException("Non hai i permessi per modificare questo contatto.");
		}

		if (u.getNome() != null)
			contatto.setNome(u.getNome());
		if (u.getCognome() != null)
			contatto.setCognome(u.getCognome());
		if (u.getEmail() != null)
			contatto.setEmail(u.getEmail());
		if (u.getTelefono() != null)
			contatto.setTelefono(u.getTelefono());

		return rep.save(contatto);
	}
}