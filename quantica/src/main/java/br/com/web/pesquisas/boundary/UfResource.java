package br.com.web.pesquisas.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Uf;
import br.com.web.pesquisas.service.UfService;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin()
@RequestMapping(value = "/api/ufs")
public class UfResource {

	@Autowired
	private UfService service;
	
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, params = "paging=false")
	public ResponseEntity<List<Uf>> findAll(
			@RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
			@RequestParam(value = "sortFields", required = false) String properties) {
		List<Uf> entities = null;

		if (properties == null || properties.isEmpty()) {
			entities = this.service.findAll();
		} else {
			Assert.notNull(properties);
			entities = this.service
					.findAll(new Sort(Sort.Direction.fromString(direction.toUpperCase()), properties.split(",")));
		}
		return new ResponseEntity<List<Uf>>(entities, HeadersUtil.generatePaginationHttpHeaders(entities.size()),
				HttpStatus.OK);

	}

}
