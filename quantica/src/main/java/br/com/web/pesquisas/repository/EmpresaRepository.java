package br.com.web.pesquisas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Empresa;

public interface EmpresaRepository extends CustomRepository<Empresa, Long>{

	@Query("select u.empresa from Usuario u where u.id = :idUsuario")
    Empresa buscaEmpresaPorUsuario(@Param("idUsuario") Long idUsuario);
	
}
