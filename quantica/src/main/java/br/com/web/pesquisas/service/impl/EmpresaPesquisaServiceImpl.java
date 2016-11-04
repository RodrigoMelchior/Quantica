package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.EmpresaPesquisa;
import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.enuns.TipoRelacionamento;
import br.com.web.pesquisas.repository.EmpresaPesquisaRepository;
import br.com.web.pesquisas.service.EmpresaPesquisaService;

@Service
public class EmpresaPesquisaServiceImpl extends CrudServiceImpl<EmpresaPesquisa, Long, EmpresaPesquisaRepository> implements EmpresaPesquisaService{

	@Override
	public EmpresaPesquisa consultarPorEmpresaAndPesquisa(Empresa empresa,Pesquisa pesquisa){
		return repository.findOneByEmpresaAndPesquisa(empresa, pesquisa);
	}

	@Override
	public EmpresaPesquisa consultarEmpresaPatriocinadora(Pesquisa pesquisa) {
		return repository.findOneByPesquisaAndRelacionamento(pesquisa, TipoRelacionamento.PATRIOCINADORA);
	}
}
