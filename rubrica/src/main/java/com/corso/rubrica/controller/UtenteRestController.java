package com.corso.rubrica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corso.rubrica.model.Utente;
import com.corso.rubrica.service.UtenteService;

@RestController
@RequestMapping("/rubrica")
@CrossOrigin(origins = "*")
public class UtenteRestController {
	@Autowired
	private UtenteService service;

	@PostMapping("/aggiungi")
	public ResponseEntity<Utente> aggiungiUtente(@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome, @RequestParam("email") String email,
			@RequestParam("telefono") String telefono, @RequestParam("eta") Integer eta) {
		try {
			Utente u = new Utente(nome, cognome, email, telefono, eta);
			return ResponseEntity.ok(service.save(u));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Utente>> getAllUtenti(@RequestParam(value="nome",required = false) String nome,
			@RequestParam(value="cognome",required=false) String cognome) {
		try {
			return ResponseEntity.ok(service.getAll(nome, cognome));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Utente> editUtente(@PathVariable("id") Integer id, @RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome, @RequestParam("email") String email,
			@RequestParam("telefono") String telefono, @RequestParam("eta") Integer eta) {
		try {
			
			Utente u = new Utente(nome, cognome, email, telefono, eta);
			return ResponseEntity.ok(service.editUtenteById(id,u));
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	@DeleteMapping("/eliminaById/{id}")
	public void eliminaUtente(@PathVariable("id") Integer id) {
		service.deleteById(id);
	}
	
	@DeleteMapping("/eliminaTutti")
	public void eliminaTuttiUtenti() {
        service.deleteAll();
    }
	
}
