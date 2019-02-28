package br.com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.app.model.db.Usuario;
import br.com.app.model.db.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

	List<Usuario> findAllByPlaca(String placa);
	
}