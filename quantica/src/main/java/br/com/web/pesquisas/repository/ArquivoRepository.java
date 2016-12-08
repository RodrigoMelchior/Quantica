package br.com.web.pesquisas.repository;

import br.com.web.pesquisas.domain.Arquivo;
import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.domain.Usuario;

public interface ArquivoRepository extends CustomRepository<Arquivo, Long>{
	Arquivo findByUsuarioAndPesquisa(Usuario usuario, Pesquisa pesquisa);
}
