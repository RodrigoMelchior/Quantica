package br.com.web.pesquisas.boundary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Caesb;
import br.com.web.pesquisas.domain.specification.CaesbSpecification;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.service.CaesbService;
import br.com.web.pesquisas.web.rest.dto.FiltroCaesbDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@RestController
@CrossOrigin(origins = "http://www.xn--pesquisasderemunerao-7yb1g.com.br")
@RequestMapping(path = "/api/caesb")
public class CaesbResource extends EntityServiceBasedRestController<Caesb, Long, CaesbService>{

    @RequestMapping(value = "/tipo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarTipo() {    
    	return service.carregaTipo();
    }
    
    @RequestMapping(value = "/empresa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarEmpresa() {    
    	return service.carregaEmpresa();
    }
    
    @RequestMapping(value = "/cod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarCod() {    
    	return service.carregaCod();
    }
    
    @RequestMapping(value = "/max", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO maiorSalario(@RequestBody FiltroCaesbDTO filtro){    
//    	if (filtro.getSetor().equalsIgnoreCase("TODOS")){
//    		filtro.setSetor("");
//    	}
    	return service.maiorSalario(filtro);
    }
    
    @RequestMapping(value = "/min", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO menorSalario(@RequestBody FiltroCaesbDTO filtro){    
//    	if (filtro.getSetor().equalsIgnoreCase("TODOS")){
//    		filtro.setSetor("");
//    	}
    	return service.menorSalario(filtro);
    }
    
    @RequestMapping(value = "/somatorio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO somatorio(@RequestBody FiltroCaesbDTO filtro){    
//    	if (filtro.getSetor().equalsIgnoreCase("TODOS")){
//    		filtro.setSetor("");
//    	}
    	return service.somatorio(filtro);
    }
    
    @RequestMapping(value = "/pesquisa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Caesb> pesquisa(){    
    	return service.pesquisa();
    }	
    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Caesb> pesquisar(@RequestBody FiltroCaesbDTO filtro) {
        
//    	if (filtro.getSetor().equalsIgnoreCase("TODOS")){
//    		filtro.setSetor("");
//    	}
        Conjunction<Caesb> specification = makeSpecification(filtro);
        List<Caesb> retorno = null;
        
        retorno = this.service.findBySpecification(specification);
        
        return retorno;   
    }
    
    private Conjunction<Caesb> makeSpecification(FiltroCaesbDTO filtro) {
	    List<Specification<Caesb>> specifications = new ArrayList<Specification<Caesb>>();

        specifications.add(CaesbSpecification.inicar());
        
	    if (StringUtils.isNotEmpty(filtro.getCod())) {
	        specifications.add(CaesbSpecification.cod(filtro.getCod()));
	    }
	    if (filtro.getEmpresa() != null) {
	        specifications.add(CaesbSpecification.empresa(filtro.getEmpresa()));
	    }
	    if (filtro.getTipo() != null) {
	        specifications.add(CaesbSpecification.tipo(filtro.getTipo()));
	    }
	    return new Conjunction<Caesb>(specifications);
	}


}
