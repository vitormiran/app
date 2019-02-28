package br.com.app.security.auth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsuarioLogado extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 3142149586210077173L;
	
	private Boolean administrador;
	private String nome;

	public UsuarioLogado(Object principal, Object credentials, String nome, Collection<? extends GrantedAuthority> authorities, Boolean administrador) {
		super(principal, credentials, authorities);
		this.nome = nome;
		this.administrador = administrador;
	}

	public Boolean isAdministrador() {
		return administrador;
	}
	
	public Boolean isNotAdministrador() {
		return !isAdministrador();
	}

	public String getNome() {
		return nome;
	}
	
	
	
	
}