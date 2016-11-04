package br.com.web.pesquisas.repository;

import java.util.List;

import br.com.web.pesquisas.domain.Municipio;
import br.com.web.pesquisas.domain.Uf;

public interface MunicipioRepository extends CustomRepository<Municipio, Long>{

	List<Municipio> findByUfOrderByNomeAsc(Uf uf);
}
