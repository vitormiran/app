package br.com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.app.model.db.Usuario;
import br.com.app.model.form.UsuarioForm;
import br.com.app.security.auth.UsuarioLogado;
import br.com.app.service.MailService;
import br.com.app.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends BaseController {

	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService, MailService mailService) {
		this.usuarioService = usuarioService;
	}


	@GetMapping({"", "/", "/index"})
	public String list(Model model, UsuarioLogado usuario) {
		model.addAttribute("listagem", this.usuarioService.list());
		return "usuario/list";
	}
	
	
	@GetMapping("/novo")
	public String novo(UsuarioForm form, Model model, HttpServletRequest request) { 
		return "usuario/novo";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid UsuarioForm form, BindingResult result, Model model, RedirectAttributes redir, HttpServletRequest request) {
		if ( result.hasErrors() ) {
			return "usuario/novo";
		}
		
		try {
			usuarioService.salvar(form);
			sucessoRegistroSalvo(redir);
			return "redirect:index";
			
		} catch (Exception e) {
			erroRegistroExistente(model);
			return "cliente/usuario/novo";
		}
	}

	@GetMapping({"/edit/{id}"})
	public String edit(UsuarioForm form, @PathVariable("id") long id, UsuarioLogado token) {
		
		Usuario usrLoad = usuarioService.get(id);
		form.setEmail(usrLoad.getEmail())
			.setNome(usrLoad.getNome());
		
		return "usuario/edit";
	}
	
	@GetMapping({"/delete/{id}"})
	public String delete(@PathVariable("id") Long id, RedirectAttributes redir, UsuarioLogado token) {
		usuarioService.delete(id);
		sucessoRegistroExcluido(redir);
		return "redirect:index";
	}


	
	
	
	
	
}