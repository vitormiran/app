package br.com.app.controller;

import java.util.Date;
import java.util.List;

import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.app.model.MensagemView;
import br.com.app.model.db.AuthenticationToken;
import br.com.app.model.db.Usuario;
import br.com.app.repository.AuthenticationTokenRepository;
import br.com.app.repository.UsuarioRepository;

public class BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	protected String LISTAGEM = "listagem";
	
	 @Autowired
	 private MessageSource messageSource;
	 
	 @Autowired
	 private AuthenticationTokenRepository authTokenRepository;
	 
	 @Autowired
	 private UsuarioRepository userRepository;
	
	protected  boolean hasAuth(String role) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		log.info(auth.getPrincipal().toString());
		log.info(auth.getAuthorities().toString() );
		
		return auth.getAuthorities().contains(new SimpleGrantedAuthority(role) );
	} 
	
	
	protected void sucessoRegistroSalvo(RedirectAttributes redir) {
		redir.addFlashAttribute("mensagem", MensagemView.buildSucesso(getMessage("msg.sucesso.registro.salvo")));
	}
	
	protected void sucessoRegistroExcluido(RedirectAttributes redir) {
		redir.addFlashAttribute("mensagem", MensagemView.buildSucesso(getMessage("msg.sucesso.registro.excluido")));
	}


	private String getMessage(String key) {
		return messageSource.getMessage(key, null, null);
	}

	protected void erroRegistroExistente(Model model) {
		erroMsg(model, getMessage("msg.erro.registro.existente"));
	}


	protected void erroMsg(Model model, String msg) {
		addMsg(model, MensagemView.buildErro(msg));
	}
	protected void infoMsg(Model model, String msg) {
		addMsg(model, MensagemView.buildInfo(msg));
	}
	protected void sucessoMsg(Model model, String msg) {
		addMsg(model, MensagemView.buildSucesso(msg));
	}
	
	private void addMsg(Model model, MensagemView msgView) {
		model.addAttribute("mensagem", msgView);
	}
	
	protected void validaUsuarioLogado(String idUsuario, String token){
		Long idUser = new Long(0);
		List<Usuario> listUser = userRepository.findAllByNome(idUsuario);
		if (listUser.size()>0) {
			idUser = listUser.get(0).getId();
		} else {
			throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
		}
		
		List<AuthenticationToken> list = authTokenRepository.findOneByIdUser(idUser);
		if (list.size()>0) {
			AuthenticationToken authToken = list.get(0);
			if (authToken.getToken().equals(token)) {
				if (!authToken.getExpiryDate().after(new Date())) {
					throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
				}
			} else {
				throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
			}
		} else {
			throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
		}
	}
}