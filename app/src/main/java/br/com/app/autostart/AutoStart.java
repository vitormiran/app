package br.com.app.autostart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import br.com.app.model.db.Usuario;
import br.com.app.repository.UsuarioRepository;

@Configuration
public class AutoStart {
	
	private static final Logger log = LoggerFactory.getLogger(AutoStart.class);

	private final UsuarioRepository usuarioRepository;
	

	public AutoStart(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}


	@Bean
	@Order(1)
	public CommandLineRunner fakeData() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception { 
				initDB();
			}
		};
	}
	
	
	private void initDB(){
		Usuario marcia = new Usuario()
				.setNome("marcia")
				.setEmail("asdqwe")
				.setSenha("123")
				.setCargo("operador")
				.setDepartamento("dp_central")
				.setSiteAlocacao("rio")
				.setTelefone("998632940");
		this.usuarioRepository.save(marcia);
		
//		Frete frete = new Frete()
//				.setCepDestino("20560000")
//				.setCepDestino("04277010")
//				.setDataEnvio("10/03/2019");
//		this.freteRepository.save(frete);
				
	}
	
}
