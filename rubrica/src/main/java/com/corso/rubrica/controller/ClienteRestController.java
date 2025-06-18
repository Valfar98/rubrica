package com.corso.rubrica.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.corso.rubrica.config.ClienteDetailsService;
import com.corso.rubrica.config.JwtUtil;
import com.corso.rubrica.config.UserDetailsImpl;
import com.corso.rubrica.dto.AuthRequest;
import com.corso.rubrica.dto.AuthResponse;
import com.corso.rubrica.dto.ClienteDTO;
import com.corso.rubrica.model.Cliente;
import com.corso.rubrica.service.ClienteService;

@RestController
@RequestMapping("/utente")
public class ClienteRestController {

	@Autowired
	private ClienteService service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			// ✅ recupera Cliente completo (che ha l'id)
			Cliente cliente = service.findByUsername(userDetails.getUsername());

			// ✅ crea DTO con id, username, ruolo
			ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getUsername(),
					cliente.getRuolo().getValore());

			String jwt = jwtUtil.generateToken(userDetails);

			// ✅ risposta finale
			AuthResponse response = new AuthResponse(jwt, clienteDTO);

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenziali non valide"));
		}
	}

	@PostMapping("/registra")
	public ResponseEntity<Map<String, Object>> registra(@RequestBody Cliente cliente) {
	    try {
	        Cliente clienteRegistrato = service.registrazione(cliente);
	        
	        UserDetails userDetails =  new UserDetailsImpl(clienteRegistrato);
	        // Genera token JWT (adatta secondo la tua implementazione)
	        String token = jwtUtil.generateToken(userDetails); 
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("token", token);
	        response.put("cliente", clienteRegistrato);
	        
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
}
