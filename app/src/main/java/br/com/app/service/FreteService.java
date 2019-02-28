package br.com.app.service;

import java.util.List;

import javax.xml.ws.http.HTTPException;

import org.springframework.stereotype.Component;

import br.com.app.model.db.Frete;
import br.com.app.model.db.Usuario;
import br.com.app.model.form.FreteForm;
import br.com.app.repository.FreteRepository;
import br.com.app.security.auth.UsuarioLogado;

@Component
public class FreteService extends BaseService {

	private FreteRepository freteRepository;
	
	public FreteService(FreteRepository freteRepository) {
		this.freteRepository = freteRepository;
	}

	public Long salvar(FreteForm form) throws HTTPException{
		Frete frete = new Frete()
				.setId(form.getId())
				.setCepDestino(form.getCepDestino())
				.setCepOrigem(form.getCepOrigem())
				.setDataEnvio(form.getDataEnvio())
				.setFreteExterno(form.isFreteExterno());
		
		
		return this.freteRepository.save(frete).getId();
	}

	public Usuario load(UsuarioLogado token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Frete get(Long id) {
		return this.freteRepository.findOne(id);
	}

	public List<Frete> list() {
		return (List<Frete>) freteRepository.findAll();
	}

	public void delete(Long id) {
		freteRepository.delete(id);
	}

	
	
	

}