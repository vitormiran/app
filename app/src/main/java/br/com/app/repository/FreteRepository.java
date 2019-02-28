package br.com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.app.model.db.Frete;

public interface FreteRepository extends CrudRepository<Frete, Long> {

	List<Frete> findAllByCepOrigem(String cepOrigem);
	List<Frete> findAllByCepDestino(String cepDestino);
	List<Frete> findAllByDataEnvio(String dataEnvio);
	
}