package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.Municipio;
import br.com.web.pesquisas.domain.Uf;

public interface CidadeService extends CrudService<Municipio, Long>{

	List<Municipio> findAllByUf(Uf uf);

}