package br.com.web.pesquisas.repository.custom;

import org.springframework.data.repository.NoRepositoryBean;

import br.com.web.pesquisas.web.rest.dto.FiltroCaesbDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@NoRepositoryBean
public interface CaesbRepositoryCustom {
	public SalDTO maiorSalario(FiltroCaesbDTO filtro);
	public SalDTO menorSalario(FiltroCaesbDTO filtro);
	public SalDTO somatorio(FiltroCaesbDTO filtro);
}
