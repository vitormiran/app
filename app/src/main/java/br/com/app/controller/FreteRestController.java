package br.com.app.controller;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.model.db.Frete;
import br.com.app.model.form.FreteForm;
import br.com.app.service.FreteService;
import br.com.app.service.MailService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/frete")
public class FreteRestController extends BaseController {

	private FreteService freteService;

	public FreteRestController(FreteService freteService, MailService mailService) {
		this.freteService = freteService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Frete> get(@PathVariable Long id) {
		Frete retorno = this.freteService.get(id);
		return new ResponseEntity<Frete>(retorno, HttpStatus.OK);

	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<String> save(@RequestBody FreteForm form) {
		Long freteId = new Long(0);
		HttpHeaders responseHeaders = new HttpHeaders();
		form.setFreteExterno(true);
		try {
			validaUsuarioLogado(form.getIdUser(), form.getAuthToken());
			freteId = freteService.salvar(form);
		} catch (HTTPException e) {
			return new ResponseEntity<String>("Usuario nao autorizado", responseHeaders, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(freteId.toString(), responseHeaders, HttpStatus.OK);

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		this.freteService.delete(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
}