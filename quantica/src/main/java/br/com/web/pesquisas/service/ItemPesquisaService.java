package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.ItemPesquisa;

public interface ItemPesquisaService extends CrudService<ItemPesquisa, Long>{

	List<ItemPesquisa> consultarPorPesquisa(Long id);

}