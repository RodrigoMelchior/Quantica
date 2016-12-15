package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.Caesb;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.web.rest.dto.FiltroCaesbDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

public interface CaesbService extends CrudService<Caesb, Long>{
	List<String> carregaTipo();
	List<String> carregaEmpresa();
	List<String> carregaCod();
	SalDTO maiorSalario(FiltroCaesbDTO filtro);
	SalDTO menorSalario(FiltroCaesbDTO filtro);
	SalDTO somatorio(FiltroCaesbDTO filtro);
	List<Caesb> pesquisa();
	List<Caesb> findBySpecification(Conjunction<Caesb> specification);
}