package br.com.app.model.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class MovimentoDataForm {

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
