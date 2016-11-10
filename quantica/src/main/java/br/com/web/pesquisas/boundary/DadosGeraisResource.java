package br.com.web.pesquisas.boundary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.DadosGeraisSpecification;
import br.com.web.pesquisas.service.DadosGeraisService;
import br.com.web.pesquisas.web.rest.dto.FiltroDadosGeraisDTO;
import br.com.web.pesquisas.web.rest.dto.SalDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/dados")
public class DadosGeraisResource extends EntityServiceBasedRestController<DadosGerais, Long, DadosGeraisService>{

    @RequestMapping(value = "/setor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarSetor() {    
    	return service.carregaSetor();
    }
    
    @RequestMapping(value = "/empresa/{setor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarEmpresa(@PathVariable(value = "setor") String setor) {    
    	return service.carregaEmpresa(setor);
    }
    
    @RequestMapping(value = "/nivel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarNivel() {    
    	return service.carregaNivel();
    }
    
    @RequestMapping(value = "/cargo/{nivel}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> consultarCargo(@PathVariable(value = "nivel") String nivel){    
    	return service.carregaCargo(nivel);
    }
    
    @RequestMapping(value = "/max", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO maiorSalario(@RequestBody FiltroDadosGeraisDTO filtro){    
    	if (filtro.getEmpresa().get(0).equalsIgnoreCase("TODOS")){
    		filtro.setEmpresa(null);
    	}
        
    	return service.maiorSalario(filtro);
    }
    
    @RequestMapping(value = "/min", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO menorSalario(@RequestBody FiltroDadosGeraisDTO filtro){    
    	if (filtro.getEmpresa().get(0).equalsIgnoreCase("TODOS")){
    		filtro.setEmpresa(null);
    	}
        
    	return service.menorSalario(filtro);
    }
    
    @RequestMapping(value = "/somatorio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO somatorio(@RequestBody FiltroDadosGeraisDTO filtro){    
    	if (filtro.getEmpresa().get(0).equalsIgnoreCase("TODOS")){
    		filtro.setEmpresa(null);
    	}

    	return service.somatorio(filtro);
    }
    
    @RequestMapping(value = "/pesquisa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DadosGerais> pesquisa(){    
    	return service.pesquisa();
    }	
    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DadosGerais> pesquisar(@RequestBody FiltroDadosGeraisDTO filtro) {
        
    	if (filtro.getEmpresa().get(0).equalsIgnoreCase("TODOS")){
    		filtro.setEmpresa(null);
    	}
        Conjunction<DadosGerais> specification = makeSpecification(filtro);
        List<DadosGerais> retorno = null;
        
        retorno = this.service.findBySpecification(specification);
        
        return retorno;   
    }
    
    private Conjunction<DadosGerais> makeSpecification(FiltroDadosGeraisDTO filtro) {
	    List<Specification<DadosGerais>> specifications = new ArrayList<Specification<DadosGerais>>();

        specifications.add(DadosGeraisSpecification.inicar());
        
	    if (StringUtils.isNotEmpty(filtro.getSetor())) {
	        specifications.add(DadosGeraisSpecification.setor(filtro.getSetor()));
	    }
	    if (filtro.getEmpresa() != null) {
	        specifications.add(DadosGeraisSpecification.empresa(filtro.getEmpresa()));
	    }
	    if (filtro.getNivel() != null) {
	        specifications.add(DadosGeraisSpecification.nivel(filtro.getNivel()));
	    }
	    if (filtro.getCargo() != null) {
	        specifications.add(DadosGeraisSpecification.cargo(filtro.getCargo()));
	    }
	    return new Conjunction<DadosGerais>(specifications);
	}


}
