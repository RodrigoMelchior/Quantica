package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.ItemPesquisa;

public interface ItemPesquisaRepository extends CustomRepository<ItemPesquisa, Long>{

	@Query("select i from ItemPesquisa i  where i.pesquisa.id = :idPesquisa order by i.ordem")
	List<ItemPesquisa> consultarPorPesquisa(@Param("idPesquisa") Long idPesquisa);
	
}
