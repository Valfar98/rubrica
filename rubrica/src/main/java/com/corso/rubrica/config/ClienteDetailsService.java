package com.corso.rubrica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.corso.rubrica.model.Cliente;
import com.corso.rubrica.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Cliente cliente = clienteRepository.findByUsername(username);
	    if(cliente==null) {
	       throw new UsernameNotFoundException("Utente non trovato");
	    }
	    return new UserDetailsImpl(cliente);
	}
}
