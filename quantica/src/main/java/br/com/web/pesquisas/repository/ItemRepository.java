package br.com.web.pesquisas.repository;

import br.com.web.pesquisas.domain.Item;

public interface ItemRepository extends CustomRepository<Item, Long>{

//	@Query("select itemPesquisa.item from ItemPesquisa itemPesquisa where itemPesquisa.empresa.id = :id order by itemPesquisa.ordem")
//	List<Item> consultarPorPesquisa(@Param("id") Long id);
	
}
