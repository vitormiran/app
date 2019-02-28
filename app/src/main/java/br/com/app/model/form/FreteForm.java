package br.com.app.model.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FreteForm {

	private Long id;

	@NotEmpty @NotNull
	private String cepOrigem;
	
	@NotEmpty @NotNull
	private String cepDestino;
	
	@NotEmpty @NotNull
	private Date dataEnvio;
	
	@NotEmpty @NotNull
	private boolean freteExterno;

	@NotEmpty @NotNull
	private String authToken;
	
	@NotEmpty @NotNull
	private String idUser;
	
	public Long getId() {
		return id;
	}

	public FreteForm setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public FreteForm setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
		return this;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public FreteForm setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
		return this;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public FreteForm setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
		return this;
	}

	public boolean isFreteExterno() {
		return freteExterno;
	}

	public FreteForm setFreteExterno(boolean freteExterno) {
		this.freteExterno = freteExterno;
		return this;
	}

	public String getAuthToken() {
		return authToken;
	}

	public FreteForm setAuthToken(String authToken) {
		this.authToken = authToken;
		return this;
	}

	public String getIdUser() {
		return idUser;
	}

	public FreteForm setIdUser(String idUser) {
		this.idUser = idUser;
		return this;
	}

	
	
}
