package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Contato;
import br.com.web.pesquisas.repository.ContatoRepository;
import br.com.web.pesquisas.service.ContatoService;

@Service
public class ContatoServiceImpl extends CrudServiceImpl<Contato, Long, ContatoRepository> implements ContatoService{

}
