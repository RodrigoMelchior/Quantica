package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Painel;
import br.com.web.pesquisas.repository.PainelRepository;
import br.com.web.pesquisas.service.PainelService;

@Service
public class PainelServiceImpl extends CrudServiceImpl<Painel, Long, PainelRepository> implements PainelService{

}
