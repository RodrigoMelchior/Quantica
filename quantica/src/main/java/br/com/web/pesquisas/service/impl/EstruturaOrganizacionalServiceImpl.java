package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.EstruturaOrganizacional;
import br.com.web.pesquisas.repository.EstruturaOrganizacionalRepository;
import br.com.web.pesquisas.service.EstruturaOrganizacionalService;

@Service
public class EstruturaOrganizacionalServiceImpl extends CrudServiceImpl<EstruturaOrganizacional, Long, EstruturaOrganizacionalRepository> implements EstruturaOrganizacionalService{

}
