package br.com.app.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticationTokenForm {

	private Long id;

	@NotEmpty @NotNull
	private String idUser;
	
	@NotEmpty @NotNull
	private String password;
	
	@NotEmpty @NotNull
	private int tempoExpiracao;
	
	
	public Long getId() {
		return id;
	}

	public AuthenticationTokenForm setId(Long id) {
		this.id = id;
		return this;
	}

	public String getIdUser() {
		return idUser;
	}

	public AuthenticationTokenForm setIdUser(String idUser) {
		this.idUser = idUser;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public AuthenticationTokenForm setPassword(String password) {
		this.password = password;
		return this;
	}

	public int getTempoExpiracao() {
		return tempoExpiracao;
	}

	public AuthenticationTokenForm setTempoExpiracao(int tempoExpiracao) {
		this.tempoExpiracao = tempoExpiracao;
		return this;
	}

	
	
}
