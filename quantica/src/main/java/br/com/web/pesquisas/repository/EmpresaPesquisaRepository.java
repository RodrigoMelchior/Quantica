package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.EmpresaPesquisa;
import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.enuns.TipoRelacionamento;

public interface EmpresaPesquisaRepository extends CustomRepository<EmpresaPesquisa, Long>{

    @Query("select empresaPesquisa.pesquisa from EmpresaPesquisa empresaPesquisa where empresaPesquisa.empresa.id = :id")
    List<Pesquisa> consultarPesquisaPorEmpresa(@Param("id") Long id);

	EmpresaPesquisa findOneByEmpresaAndPesquisa(Empresa empresa, Pesquisa pesquisa);

	EmpresaPesquisa findOneByPesquisaAndRelacionamento(Pesquisa pesquisa, TipoRelacionamento relacionamento);
	
	
    
}
