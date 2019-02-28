package br.com.app.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String placa;

	@NotNull
	private String modelo;

	@NotNull
	private String chassi;

	@NotNull
	private String kmRodados;

	@NotNull
	private String vencimentoSeguro;

	@NotNull
	private Boolean disponivel;

	public Long getId() {
		return id;
	}

	public Veiculo setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPlaca() {
		return placa;
	}

	public Veiculo setPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public String getModelo() {
		return modelo;
	}

	public Veiculo setModelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public String getChassi() {
		return chassi;
	}

	public Veiculo setChassi(String chassi) {
		this.chassi = chassi;
		return this;
	}

	public String getKmRodados() {
		return kmRodados;
	}

	public Veiculo setKmRodados(String kmRodados) {
		this.kmRodados = kmRodados;
		return this;
	}

	public String getVencimentoSeguro() {
		return vencimentoSeguro;
	}

	public Veiculo setVencimentoSeguro(String vencimentoSeguro) {
		this.vencimentoSeguro = vencimentoSeguro;
		return this;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public Veiculo setDisponivel(Boolean status) {
		this.disponivel = status;
		return this;
	}






}
