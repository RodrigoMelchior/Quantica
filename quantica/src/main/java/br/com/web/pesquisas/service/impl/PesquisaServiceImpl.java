package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.repository.PesquisaRepository;
import br.com.web.pesquisas.service.PesquisaService;

@Service
public class PesquisaServiceImpl extends CrudServiceImpl<Pesquisa, Long, PesquisaRepository> implements PesquisaService{

	@Override
	public List<Pesquisa> consultarPesquisaPorEmpresa(Long id){
		return repository.consultarPesquisaPorEmpresa(id);
	}
}
