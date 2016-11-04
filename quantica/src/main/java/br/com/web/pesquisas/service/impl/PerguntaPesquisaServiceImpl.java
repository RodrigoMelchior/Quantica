package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.PerguntaPesquisa;
import br.com.web.pesquisas.repository.PerguntaPesquisaRepository;
import br.com.web.pesquisas.service.PerguntaPesquisaService;

@Service
public class PerguntaPesquisaServiceImpl extends CrudServiceImpl<PerguntaPesquisa, Long, PerguntaPesquisaRepository> implements PerguntaPesquisaService{

}
