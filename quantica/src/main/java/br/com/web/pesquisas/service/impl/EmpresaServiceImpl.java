package br.com.web.pesquisas.service.impl;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.repository.EmpresaRepository;
import br.com.web.pesquisas.service.EmpresaService;

@Service
public class EmpresaServiceImpl extends CrudServiceImpl<Empresa, Long, EmpresaRepository> implements EmpresaService{

	public Empresa buscaEmpresaPorUsuario(@Param("idUsuario") Long idUsuario){
		return repository.buscaEmpresaPorUsuario(idUsuario);
	}

}
