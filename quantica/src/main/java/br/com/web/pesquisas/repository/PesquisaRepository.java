package br.com.web.pesquisas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Pesquisa;

public interface PesquisaRepository extends CustomRepository<Pesquisa, Long>{

    @Query("select empresaPesquisa.pesquisa from EmpresaPesquisa empresaPesquisa where empresaPesquisa.empresa.id = :id")
    List<Pesquisa> consultarPesquisaPorEmpresa(@Param("id") Long id);
    
}
