package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Caesb;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.repository.CaesbRepository;
import br.com.web.pesquisas.service.CaesbService;
import br.com.web.pesquisas.web.rest.dto.FiltroCaesbDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@Service
public class CaesbServiceImpl extends CrudServiceImpl<Caesb, Long, CaesbRepository> implements CaesbService{

	@Override
	public List<String> carregaTipo() {
		return repository.carregaTipo();
	}

	@Override
	public List<String> carregaEmpresa() {
		return repository.carregaEmpresa();
	}

	@Override
	public List<String> carregaCod() {
		return repository.carregaCod();
	}

	@Override
	public SalDTO maiorSalario(FiltroCaesbDTO filtro) {
		return repository.maiorSalario(filtro);
	}

	@Override
	public SalDTO menorSalario(FiltroCaesbDTO filtro) {
		return repository.menorSalario(filtro);
	}

	@Override
	public SalDTO somatorio(FiltroCaesbDTO filtro) {
		return repository.somatorio(filtro);
	}
	
	@Override
	public List<Caesb> pesquisa() {
		return repository.pesquisa();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Caesb> findBySpecification(Conjunction<Caesb> specification) {
		return repository.findAll(specification);
	}
}
