package br.com.web.pesquisas.service;

import org.springframework.data.repository.query.Param;

import br.com.web.pesquisas.domain.Empresa;

public interface EmpresaService extends CrudService<Empresa, Long>{
	Empresa buscaEmpresaPorUsuario(@Param("idUsuario") Long idUsuario);
}