package br.com.app.controller;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.model.form.AuthenticationTokenForm;
import br.com.app.service.AuthenticationTokenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/authToken")
public class AuthenticationTokenRestController extends BaseController {

	private AuthenticationTokenService authService;

	public AuthenticationTokenRestController(AuthenticationTokenService authService) {
		this.authService = authService;
	}

//	@GetMapping({"", "/"})
//	public ResponseEntity<List<Frete>> list(Model model, UsuarioLogado usuario) {
//		List<Frete> retorno = this.freteService.list();
//		return new ResponseEntity<List<Frete>>(retorno, HttpStatus.OK);
//
//	}

	@PostMapping({"", "/"})
	public ResponseEntity<String> save(@RequestBody AuthenticationTokenForm form) {
		String token = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		
		try {
			token = authService.salvar(form);
		} catch (HTTPException e) {
			return new ResponseEntity<String>("Usuario nao autorizado", responseHeaders, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(token, responseHeaders, HttpStatus.OK);

	}

	
}