package br.com.web.pesquisas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.PerguntaPesquisa;
import br.com.web.pesquisas.domain.Resposta;
import br.com.web.pesquisas.repository.RespostaRepository;
import br.com.web.pesquisas.service.RespostaService;

@Service
public class RespostaServiceImpl extends CrudServiceImpl<Resposta, Long, RespostaRepository> implements RespostaService{

	@Override
	public List<Resposta> findByPerguntaAndEmpresa(PerguntaPesquisa perguntaPesquisa, Empresa empresa) {
		return repository.findByPerguntaAndEmpresa(perguntaPesquisa, empresa);
	}

}
