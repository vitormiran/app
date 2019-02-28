package br.com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.app.model.db.AuthenticationToken;
import br.com.app.model.db.Usuario;

public interface AuthenticationTokenRepository extends CrudRepository<AuthenticationToken, Long> {

	List<AuthenticationToken> findOneByIdUser(Long idUser);
	
	
}