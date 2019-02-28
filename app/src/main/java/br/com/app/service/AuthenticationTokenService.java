package br.com.app.service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.app.model.db.AuthenticationToken;
import br.com.app.model.db.Usuario;
import br.com.app.model.form.AuthenticationTokenForm;
import br.com.app.repository.AuthenticationTokenRepository;
import br.com.app.repository.UsuarioRepository;

@Component
public class AuthenticationTokenService extends BaseService {

	private AuthenticationTokenRepository authRepository;
	private UsuarioRepository usuarioRepository;
	
	public AuthenticationTokenService(AuthenticationTokenRepository authRepository, UsuarioRepository usuarioRepository) {
		this.authRepository = authRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public String salvar(AuthenticationTokenForm form) throws HTTPException{
		AuthenticationToken authToken = new AuthenticationToken();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, form.getTempoExpiracao());
		Date dataExpiracao = cal.getTime();
		authToken.setExpiryDate(dataExpiracao);
		
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[80];
		random.nextBytes(bytes);
		String token = bytes.toString();
		authToken.setToken(token);
		
		List<Usuario> user = this.usuarioRepository.findOneByNome(form.getIdUser());
		if (user.size()>0) {
			if (!user.get(0).getSenha().equals(form.getPassword())) {
				throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
			}
			System.out.println("Usuario autenticado com sucesso");
			authToken.setIdUser(user.get(0).getId());
		} else {
			throw new HTTPException(HttpStatus.UNAUTHORIZED.value());
		}
		
		this.authRepository.save(authToken);
		return authToken.getToken();
				
	}

//	public Usuario load(UsuarioLogado token) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	public Frete get(Long id) {
//		return this.freteRepository.findOne(id);
//	}
//
//	public List<Frete> list() {
//		return (List<Frete>) freteRepository.findAll();
//	}

		

	
	
	

}