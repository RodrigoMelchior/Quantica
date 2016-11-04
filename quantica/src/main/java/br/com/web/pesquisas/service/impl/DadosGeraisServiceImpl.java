package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.repository.DadosGeraisRepository;
import br.com.web.pesquisas.service.DadosGeraisService;
import br.com.web.pesquisas.web.rest.dto.FiltroDadosGeraisDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@Service
public class DadosGeraisServiceImpl extends CrudServiceImpl<DadosGerais, Long, DadosGeraisRepository> implements DadosGeraisService{

	@Override
	public List<String> carregaSetor() {
		return repository.carregaSetor();
	}

	@Override
	public List<String> carregaEmpresa(String setor) {
		if (setor.isEmpty() || setor.equals("TODOS")){
			return repository.carregaEmpresa();
		}else{
			return repository.carregaEmpresa(setor);
		}
	}

	@Override
	public List<String> carregaNivel() {
		return repository.carregaNivel();
	}

	@Override
	public List<String> carregaCargo(String nivel) {
		if (nivel.isEmpty() || nivel.equals("TODOS")){
			return repository.carregaCargo();
		}else{
			return repository.carregaCargo(nivel);
		}
	}

	@Override
	public SalDTO maiorSalario(FiltroDadosGeraisDTO filtro) {
		return repository.maiorSalario(filtro);
	}

	@Override
	public SalDTO menorSalario(FiltroDadosGeraisDTO filtro) {
		return repository.menorSalario(filtro);
	}

	@Override
	public SalDTO somatorio(FiltroDadosGeraisDTO filtro) {
		return repository.somatorio(filtro);
	}
	
	@Override
	public List<DadosGerais> pesquisa() {
		return repository.pesquisa();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DadosGerais> findBySpecification(Conjunction<DadosGerais> specification) {
		return repository.findAll(specification);
	}
}
