package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.repository.custom.DadosGeraisRepositoryCustom;

public interface DadosGeraisRepository extends CustomRepository<DadosGerais, Long>, DadosGeraisRepositoryCustom{


	@Query("select distinct dg.setor from DadosGerais dg")
	List<String> carregaSetor();

	@Query("select distinct dg.empresa from DadosGerais dg")
	List<String> carregaEmpresa();

	@Query("select distinct dg.empresa from DadosGerais dg where dg.setor = :setor")
	List<String> carregaEmpresa(@Param("setor") String setor);

	@Query("select distinct dg.nivel from DadosGerais dg")
	List<String> carregaNivel();

	@Query("select distinct dg.cargo from DadosGerais dg")
	List<String> carregaCargo();

	@Query("select distinct dg.cargo from DadosGerais dg where dg.nivel = :nivel")
	List<String> carregaCargo(@Param("nivel") String nivel);

	@Query("select dg from DadosGerais dg ")
	List<DadosGerais> pesquisa();

}
