package br.com.web.pesquisas.service;

import java.util.List;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.PerguntaPesquisa;
import br.com.web.pesquisas.domain.Resposta;

public interface RespostaService extends CrudService<Resposta, Long>{
	List<Resposta> findByPerguntaAndEmpresa(PerguntaPesquisa perguntaPesquisa, Empresa empresa);
}