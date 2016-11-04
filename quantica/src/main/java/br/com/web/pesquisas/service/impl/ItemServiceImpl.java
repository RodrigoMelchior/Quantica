package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Item;
import br.com.web.pesquisas.repository.ItemRepository;
import br.com.web.pesquisas.service.ItemService;

@Service
public class ItemServiceImpl extends CrudServiceImpl<Item, Long, ItemRepository> implements ItemService{

	@Override
	public List<Item> consultarPorPesquisa(Long id) {
		//return repository.consultarPorPesquisa(id);
		return null;
	}

}
