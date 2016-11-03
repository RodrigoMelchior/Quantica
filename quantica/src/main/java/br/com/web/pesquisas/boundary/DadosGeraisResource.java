package br.com.web.pesquisas.boundary;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.DadosGerais;
import br.com.web.pesquisas.service.DadosGeraisService;
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
    
    @RequestMapping(value = "/max", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO maiorSalario(){    
    	return service.maiorSalario();
    }
    
    @RequestMapping(value = "/min", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO menorSalario(){    
    	return service.menorSalario();
    }
    
    @RequestMapping(value = "/somatorio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalDTO somatorio(){    
    	return service.somatorio();
    }
    
    @RequestMapping(value = "/pesquisa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DadosGerais> pesquisa(){    
    	return service.pesquisa();
    }	
}
