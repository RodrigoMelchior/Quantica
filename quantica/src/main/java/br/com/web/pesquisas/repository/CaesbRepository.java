package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.web.pesquisas.domain.Caesb;
import br.com.web.pesquisas.repository.custom.CaesbRepositoryCustom;

public interface CaesbRepository extends CustomRepository<Caesb, Long>, CaesbRepositoryCustom{


	@Query("select distinct dg.tipo from Caesb dg order by dg.tipo")
	List<String> carregaTipo();

	@Query("select distinct dg.empresa from Caesb dg order by dg.empresa")
	List<String> carregaEmpresa();

	@Query("select distinct dg.nome from Caesb dg  order by dg.nome")
	List<String> carregaCod();

	@Query("select dg from Caesb dg ")
	List<Caesb> pesquisa();

}
