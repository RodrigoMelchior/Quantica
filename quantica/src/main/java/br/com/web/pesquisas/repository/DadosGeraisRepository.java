package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

public interface DadosGeraisRepository extends CustomRepository<DadosGerais, Long>{

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

	@Query("select new br.com.web.pesquisas.web.rest.dto.SalDTO(max(dg.sal1), max(dg.sal2), max(dg.sal3), max(dg.sal4), max(dg.sal5)) from DadosGerais dg ")
	SalDTO maiorSalario();

	@Query("select new br.com.web.pesquisas.web.rest.dto.SalDTO(min(dg.sal1), min(dg.sal2), min(dg.sal3), min(dg.sal4), min(dg.sal5)) from DadosGerais dg ")
	SalDTO menorSalario();

	@Query("select new br.com.web.pesquisas.web.rest.dto.SalDTO(sum(dg.sal1), sum(dg.sal2), sum(dg.sal3), sum(dg.sal4), sum(dg.sal5)) from DadosGerais dg ")
	SalDTO somatorio();

	@Query("select dg from DadosGerais dg ")
	List<DadosGerais> pesquisa();

}
