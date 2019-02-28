package br.com.app.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String telefone;

	@NotNull
	private String cargo;

	@NotNull
	private String departamento;

	@NotNull
	private String siteAlocacao;
	

	public String getTelefone() {
		return telefone;
	}

	public Usuario setTelefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	public String getCargo() {
		return cargo;
	}

	public Usuario setCargo(String cargo) {
		this.cargo = cargo;
		return this;
	}

	public String getDepartamento() {
		return departamento;
	}

	public Usuario setDepartamento(String departamento) {
		this.departamento = departamento;
		return this;
	}

	public String getSiteAlocacao() {
		return siteAlocacao;
	}

	public Usuario setSiteAlocacao(String siteAlocacao) {
		this.siteAlocacao = siteAlocacao;
		return this;
	}

	public Long getId() {
		return id;
	}

	public Usuario setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Usuario setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario setSenha(String senha) {
		this.senha = senha;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Usuario setEmail(String email) {
		this.email = email;
		return this;
	}
	

	
	
}
