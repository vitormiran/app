package br.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.app.service.FreteService;
import br.com.app.service.MailService;

@Controller
@RequestMapping("/frete")
public class FreteController extends BaseController {

	private FreteService freteService;
	
	public FreteController(FreteService freteService, MailService mailService) {
		this.freteService = freteService;
	}


	@GetMapping({"", "/", "/index"})
	public String list(Model model) {
		model.addAttribute("listagem", this.freteService.list());
		return "frete/list";
	}
	
	
//	@GetMapping("/novo")
//	public String novo(UsuarioForm form, Model model, HttpServletRequest request) { 
//		return "usuario/novo";
//	}
//	
//	@PostMapping("/salvar")
//	public String salvar(@Valid UsuarioForm form, BindingResult result, Model model, RedirectAttributes redir, HttpServletRequest request) {
//		if ( result.hasErrors() ) {
//			return "usuario/novo";
//		}
//		
//		try {
//			usuarioService.salvar(form);
//			sucessoRegistroSalvo(redir);
//			return "redirect:index";
//			
//		} catch (Exception e) {
//			erroRegistroExistente(model);
//			return "cliente/usuario/novo";
//		}
//	}
//
//	@GetMapping({"/edit/{id}"})
//	public String edit(UsuarioForm form, @PathVariable("id") long id, UsuarioLogado token) {
//		
//		Usuario usrLoad = usuarioService.get(id);
//		form.setEmail(usrLoad.getEmail())
//			.setNome(usrLoad.getNome());
//		
//		return "usuario/edit";
//	}
//	
//	@GetMapping({"/delete/{id}"})
//	public String delete(@PathVariable("id") Long id, RedirectAttributes redir, UsuarioLogado token) {
//		usuarioService.delete(id);
//		sucessoRegistroExcluido(redir);
//		return "redirect:index";
//	}


	
	
	
	
	
}