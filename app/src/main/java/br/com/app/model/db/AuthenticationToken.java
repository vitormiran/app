package br.com.app.model.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHENTICATIONTOKEN")
public class AuthenticationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String token;
	
	@NotNull
	private Date expiryDate;
	
	@NotNull
	private Long idUser;
	
	public Long getIdUser() {
		return idUser;
	}

	public AuthenticationToken setIdUser(Long idUser) {
		this.idUser = idUser;
		return this;
	}

	public Long getId() {
		return id;
	}

	public AuthenticationToken setId(Long id) {
		this.id = id;
		return this;
	}

	public String getToken() {
		return token;
	}

	public AuthenticationToken setToken(String hashCode) {
		this.token = hashCode;
		return this;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public AuthenticationToken setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

		
	
	
	
}
