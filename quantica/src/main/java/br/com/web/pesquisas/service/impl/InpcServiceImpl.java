package br.com.web.pesquisas.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Inpc;
import br.com.web.pesquisas.repository.InpcRepository;
import br.com.web.pesquisas.service.InpcService;

@Service
public class InpcServiceImpl extends CrudServiceImpl<Inpc, Long, InpcRepository> implements InpcService{

	@SuppressWarnings("null")
	@Override
	public BigDecimal calculaIndiceReajuste(Long idEmpresa, Long idPesquisa){
		BigDecimal indReajuste = null;
		List<BigDecimal> indices = null; // repository.buscaIndicesParaReajuste(idEmpresa, idPesquisa);
		for (BigDecimal indice : indices) {
			if (indReajuste != null){
				indReajuste = indReajuste.multiply(indice);
			}else{
				indReajuste = indice;
			}
		}
		return indReajuste;
	}
}
