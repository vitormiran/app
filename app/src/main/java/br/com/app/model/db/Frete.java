package br.com.app.model.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FRETE")
public class Frete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String cepOrigem;
	
	@NotNull
	private String cepDestino;
	
	@NotNull
	private Date dataEnvio;
	
	@NotNull
	private boolean freteExterno;

	public Long getId() {
		return id;
	}

	public Frete setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public Frete setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
		return this;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public Frete setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
		return this;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public Frete setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
		return this;
	}

	public boolean isFreteExterno() {
		return freteExterno;
	}

	public Frete setFreteExterno(boolean freteExterno) {
		this.freteExterno = freteExterno;
		return this;
	}
	
	
	
	
}
