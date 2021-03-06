package br.com.web.pesquisas.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Inpc;

public interface InpcRepository extends CustomRepository<Inpc, Long>{

    @Query("select (i.indice / 100) + 1 from Inpc i where mesAno between ("
    		+ "select e.dataUltimoReajuste from Empresa e where id = :idEmpresa"
    		+ ") and ("
    		+ "select ep.dataUltimoReajuste from Empresa ep where id = ("
    		+ "select rlep.empresa.id from EmpresaPesquisa rlep where rlep.pesquisa.id = :idPesquisa))")
    List<BigDecimal> buscaIndicesParaReajuste(@Param("idEmpresa") Long idEmpresa, @Param("idPesquisa") Long idPesquisa);
	
}
