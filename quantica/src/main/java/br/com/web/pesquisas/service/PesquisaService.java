package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.Pesquisa;

public interface PesquisaService extends CrudService<Pesquisa, Long>{

	List<Pesquisa> consultarPesquisaPorEmpresa(Long id);

}