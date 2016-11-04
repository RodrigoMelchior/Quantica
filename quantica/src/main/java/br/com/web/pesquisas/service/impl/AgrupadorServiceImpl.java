package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Agrupador;
import br.com.web.pesquisas.repository.AgrupadorRepository;
import br.com.web.pesquisas.service.AgrupadorService;

@Service
public class AgrupadorServiceImpl extends CrudServiceImpl<Agrupador, Long, AgrupadorRepository> implements AgrupadorService{

}
