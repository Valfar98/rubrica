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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corso.rubrica.model.Contatto;
import com.corso.rubrica.service.ContattoService;

@RestController
@RequestMapping("/contatti")
public class ContattoRestController {
	@Autowired
	private ContattoService service;

	@PostMapping()
	public ResponseEntity<Contatto> aggiungiUtente(@RequestBody Contatto u) {
		try {
			return ResponseEntity.ok(service.save(u));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@GetMapping()
	public ResponseEntity<List<Contatto>> getAllUtenti(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cognome", required = false) String cognome) {
		try {
			return ResponseEntity.ok(service.getAll(nome, cognome));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contatto> editUtente(@PathVariable("id") Integer id, @RequestBody Contatto u) {
		try {

			return ResponseEntity.ok(service.editUtenteById(id, u));
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return ResponseEntity.internalServerError().body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminaUtente(@PathVariable("id") Integer id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}

	@DeleteMapping
	public ResponseEntity<Void> eliminaTuttiUtenti() {
		try {
			service.deleteAll();
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
