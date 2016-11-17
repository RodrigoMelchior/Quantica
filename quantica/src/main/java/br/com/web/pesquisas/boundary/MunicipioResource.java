package br.com.web.pesquisas.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Municipio;
import br.com.web.pesquisas.domain.Uf;
import br.com.web.pesquisas.service.CidadeService;
import br.com.web.pesquisas.service.UfService;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin()
@RequestMapping(value="/api/ufs/{idUf:\\d+}/municipios")
public class MunicipioResource {
	
	private CidadeService service;
	
	private UfService ufService;
	
	@Autowired
	public MunicipioResource(CidadeService cidadeService, UfService ufService){
		this.service = cidadeService;
		this.ufService = ufService;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, params = "paging=false")
	public ResponseEntity<List<Municipio>> findAllByUf(
			@PathVariable("idUf") Long idUf) {
		
		Uf uf = ufService.findById(idUf);


		List<Municipio>	entities = this.service.findAllByUf(uf);
		return new ResponseEntity<List<Municipio>>(entities, HeadersUtil.generatePaginationHttpHeaders(entities.size()),
				HttpStatus.OK);
	}

}
