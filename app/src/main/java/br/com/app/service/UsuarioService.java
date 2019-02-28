package br.com.app.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.app.model.db.Usuario;
import br.com.app.model.form.UsuarioForm;
import br.com.app.repository.UsuarioRepository;
import br.com.app.security.auth.UsuarioLogado;

@Component
public class UsuarioService extends BaseService {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public void salvar(UsuarioForm form) {
		Usuario usr = new Usuario()
				.setId(form.getId())
				.setEmail(form.getEmail())
				.setNome(form.getNome())
				.setSenha("123");
		this.usuarioRepository.save(usr);
				
	}

	public Usuario load(UsuarioLogado token) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Usuario get(Long id) {
		return this.usuarioRepository.findOne(id);
	}

	public List<Usuario> list() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public void delete(Long id) {
		usuarioRepository.delete(id);
	}

	
	
	

}