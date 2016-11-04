package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.ItemPesquisa;
import br.com.web.pesquisas.repository.ItemPesquisaRepository;
import br.com.web.pesquisas.service.ItemPesquisaService;

@Service
public class ItemPesquisaServiceImpl extends CrudServiceImpl<ItemPesquisa, Long, ItemPesquisaRepository> implements ItemPesquisaService{

	@Override
	public List<ItemPesquisa> consultarPorPesquisa(Long id) {
		return repository.consultarPorPesquisa(id);
	}

}
