package br.com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.model.db.Usuario;
import br.com.app.model.form.UsuarioForm;
import br.com.app.security.auth.UsuarioLogado;
import br.com.app.service.MailService;
import br.com.app.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController extends BaseController {

	private UsuarioService usuarioService;

	public UsuarioRestController(UsuarioService usuarioService, MailService mailService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping({"", "/"})
	public ResponseEntity<List<Usuario>> list(Model model, UsuarioLogado usuario) {
		List<Usuario> retorno = this.usuarioService.list();
		return new ResponseEntity<List<Usuario>>(retorno, HttpStatus.OK);

	}

	@PostMapping({"", "/"})
	public ResponseEntity save(@RequestBody UsuarioForm form) {
		usuarioService.salvar(form);
		return new ResponseEntity<>(HttpStatus.OK);

	}




}