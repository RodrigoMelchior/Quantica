package br.com.web.pesquisas.service;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.EmpresaPesquisa;
import br.com.web.pesquisas.domain.Pesquisa;

public interface EmpresaPesquisaService extends CrudService<EmpresaPesquisa, Long>{

	EmpresaPesquisa consultarPorEmpresaAndPesquisa(Empresa empresa, Pesquisa pesquisa);

	EmpresaPesquisa consultarEmpresaPatriocinadora(Pesquisa pesquisa);

}