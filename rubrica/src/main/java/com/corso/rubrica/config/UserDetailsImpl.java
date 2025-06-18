package com.corso.rubrica.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.corso.rubrica.model.Cliente;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -4914345032742352835L;
	
	private final Cliente cliente;
	
	public UserDetailsImpl(Cliente cliente) {
        this.cliente = cliente;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of(new SimpleGrantedAuthority("ROLE_" + cliente.getRuolo().name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return cliente.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return cliente.getUsername();
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }


}
