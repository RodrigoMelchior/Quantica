package br.com.web.pesquisas.service;

import java.math.BigDecimal;

import br.com.web.pesquisas.domain.Inpc;

public interface InpcService extends CrudService<Inpc, Long>{
	public BigDecimal calculaIndiceReajuste(Long idEmpresa, Long idPesquisa);
}