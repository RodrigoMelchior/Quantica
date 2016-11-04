package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.Item;

public interface ItemService extends CrudService<Item, Long>{

	List<Item> consultarPorPesquisa(Long id);

}