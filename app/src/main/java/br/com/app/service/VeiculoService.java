package br.com.app.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.app.model.db.Veiculo;
import br.com.app.model.form.VeiculoForm;
import br.com.app.repository.VeiculoRepository;

@Component
public class VeiculoService extends BaseService {

	private VeiculoRepository veiculoRepository;
	
	public VeiculoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}

	public void salvar(VeiculoForm form) {
		Veiculo usr = new Veiculo()
				.setId(form.getId())
				.setChassi(form.getChassi())
				.setKmRodados(form.getKmRodados())
				.setModelo(form.getModelo())
				.setPlaca(form.getPlaca())
				.setVencimentoSeguro(form.getVencimentoSeguro())
				.setDisponivel(form.getDisponivel());
				this.veiculoRepository.save(usr);
	}
	
	public Veiculo get(Long id) {
		return this.veiculoRepository.findOne(id);
	}

	public List<Veiculo> list() {
		return (List<Veiculo>) veiculoRepository.findAll();
	}

	public void delete(Long id) {
		veiculoRepository.delete(id);
	}

	
	
	

}