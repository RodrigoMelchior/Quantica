package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.web.rest.dto.FiltroDadosGeraisDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

public interface DadosGeraisService extends CrudService<DadosGerais, Long>{
	List<String> carregaSetor();
	List<String> carregaEmpresa(String setor);
	List<String> carregaNivel();
	List<String> carregaCargo(String nivel);
	SalDTO maiorSalario(FiltroDadosGeraisDTO filtro);
	SalDTO menorSalario(FiltroDadosGeraisDTO filtro);
	SalDTO somatorio(FiltroDadosGeraisDTO filtro);
	List<DadosGerais> pesquisa();
	List<DadosGerais> findBySpecification(Conjunction<DadosGerais> specification);
}