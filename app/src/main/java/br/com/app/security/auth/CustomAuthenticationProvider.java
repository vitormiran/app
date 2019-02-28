package br.com.app.security.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.app.model.db.Usuario;
import br.com.app.repository.UsuarioRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
 
	private UsuarioRepository usuarioRepository;
	
	public CustomAuthenticationProvider(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
        String senha = authentication.getCredentials().toString();
        
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        String dominio = request.getServerName().split("\\.")[0];
        
        List<Usuario> usuarios = usuarioRepository.findAllByNome(email)
        	.stream()
        	//.filter(usuario -> dominio.equals(usuario.getContabilidade().getDominio()))
        	.filter(usuario -> usuario.getSenha().equals(senha) )
        	.collect(Collectors.toList());
        
        if (usuarios.isEmpty() || usuarios.size() > 1){
        	return null;
        	
        } else {
        	
        	
         	
        	Usuario usuarioAutenticado = usuarios.iterator().next();
        	
        	
        	List<SimpleGrantedAuthority> roles = buildRoles(usuarioAutenticado);
			return new UsuarioLogado(email, senha, usuarioAutenticado.getNome(), roles, Boolean.TRUE);	
        }
	}



	private List<SimpleGrantedAuthority> buildRoles(Usuario usuarioAutenticado) {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return list;
	}
 


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
 
}


