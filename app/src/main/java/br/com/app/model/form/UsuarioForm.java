package br.com.app.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioForm {

	private Long id;

	@NotEmpty @NotNull
	private String nome;
	
	@NotEmpty @NotNull
	private String email;
	
	@NotEmpty @NotNull
	private String telefone;

	@NotEmpty @NotNull
	private String cargo;

	@NotEmpty @NotNull
	private String departamento;

	@NotEmpty @NotNull
	private String siteAlocacao;
	
	
	public String getTelefone() {
		return telefone;
	}

	public UsuarioForm setTelefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	public String getCargo() {
		return cargo;
	}

	public UsuarioForm setCargo(String cargo) {
		this.cargo = cargo;
		return this;
	}

	public String getDepartamento() {
		return departamento;
	}

	public UsuarioForm setDepartamento(String departamento) {
		this.departamento = departamento;
		return this;
	}

	public String getSiteAlocacao() {
		return siteAlocacao;
	}

	public UsuarioForm setSiteAlocacao(String siteAlocacao) {
		this.siteAlocacao = siteAlocacao;
		return this;
	}


	public Long getId() {
		return id;
	}

	public UsuarioForm setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public UsuarioForm setNome(String nome) {
		this.nome = nome;
		return this;
	}


	public String getEmail() {
		return email;
	}

	public UsuarioForm setEmail(String email) {
		this.email = email;
		return this;
	}
	
	
}
