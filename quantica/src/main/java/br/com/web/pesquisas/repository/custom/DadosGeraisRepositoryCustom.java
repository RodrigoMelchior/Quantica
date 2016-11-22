package br.com.web.pesquisas.repository.custom;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.web.rest.dto.FiltroDadosGeraisDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@NoRepositoryBean
public interface DadosGeraisRepositoryCustom {
	public SalDTO maiorSalario(FiltroDadosGeraisDTO filtro);
	public SalDTO menorSalario(FiltroDadosGeraisDTO filtro);
	public SalDTO somatorio(FiltroDadosGeraisDTO filtro);
	List<DadosGerais> consultar(FiltroDadosGeraisDTO filtro);
}
