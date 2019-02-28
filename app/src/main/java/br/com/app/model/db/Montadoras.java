package br.com.app.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MONTADORA")
public class Montadoras	 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String marca;
		

	public String getMarca() {
		return marca;
	}

	public Montadoras setMarca(String marca) {
		this.marca = marca;
		return this;
	}

		
	
}
