package br.com.app.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class VeiculoForm {

	private Long id;

	@NotEmpty @NotNull
	private String placa;
	
	@NotEmpty @NotNull
	private String modelo;
	
	@NotEmpty @NotNull
	private String chassi;

	@NotEmpty @NotNull
	private String kmRodados;

	@NotEmpty @NotNull
	private String vencimentoSeguro;
	
	@NotNull
	private Boolean disponivel;


	public Long getId() {
		return id;
	}

	public VeiculoForm setId(Long id) {
		this.id = id;
		return this;
	}

	public String getModelo() {
		return modelo;
	}

	public VeiculoForm setModelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public String getChassi() {
		return chassi;
	}

	public VeiculoForm setChassi(String chassi) {
		this.chassi = chassi;
		return this;
	}

	public String getKmRodados() {
		return kmRodados;
	}

	public VeiculoForm setKmRodados(String kmRodados) {
		this.kmRodados = kmRodados;
		return this;
	}

	public String getVencimentoSeguro() {
		return vencimentoSeguro;
	}

	public VeiculoForm setVencimentoSeguro(String vencimentoSeguro) {
		this.vencimentoSeguro = vencimentoSeguro;
		return this;
	}


	public String getPlaca() {
		return placa;
	}

	public VeiculoForm setPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public VeiculoForm setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
		return this;
	}

	
}
