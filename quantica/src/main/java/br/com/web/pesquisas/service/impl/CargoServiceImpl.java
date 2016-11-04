package br.com.web.pesquisas.service.impl;

import org.springframework.stereotype.Service;

import br.com.web.pesquisas.domain.Cargo;
import br.com.web.pesquisas.repository.CargoRepository;
import br.com.web.pesquisas.service.CargoService;

@Service
public class CargoServiceImpl extends CrudServiceImpl<Cargo, Long, CargoRepository> implements CargoService{

}
