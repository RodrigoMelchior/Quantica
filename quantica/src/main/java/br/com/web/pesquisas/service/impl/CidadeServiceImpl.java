package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Municipio;
import br.com.web.pesquisas.domain.Uf;
import br.com.web.pesquisas.repository.MunicipioRepository;
import br.com.web.pesquisas.service.CidadeService;

@Service("CidadeService")
public class CidadeServiceImpl extends CrudServiceImpl<Municipio, Long, MunicipioRepository> implements CidadeService{
	
	@Override
	public List<Municipio> findAllByUf(Uf uf){
		return repository.findByUfOrderByNomeAsc(uf);
	}

}
