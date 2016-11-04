package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Pergunta;
import br.com.web.pesquisas.repository.PerguntaRepository;
import br.com.web.pesquisas.service.PerguntaService;

@Service
public class PerguntaServiceImpl extends CrudServiceImpl<Pergunta, Long, PerguntaRepository> implements PerguntaService{

}
