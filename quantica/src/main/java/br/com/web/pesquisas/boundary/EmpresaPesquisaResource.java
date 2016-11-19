package br.com.web.pesquisas.boundary;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.EmpresaPesquisa;
import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.service.EmpresaPesquisaService;

@RestController
@CrossOrigin(origins = "http://www.xn--pesquisasderemunerao-7yb1g.com.br")
@RequestMapping(path = "/api/empresaPesquisas")
public class EmpresaPesquisaResource extends EntityServiceBasedRestController<EmpresaPesquisa, Long, EmpresaPesquisaService>{

    @RequestMapping(value = "/por-empresa-pesquisa/{idEmpresa}/{idPesquisa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmpresaPesquisa consultarPorEmpresaAndPesquisa(
    			@PathVariable(value = "idEmpresa") Long idEmpresa,
    			@PathVariable(value = "idPesquisa") Long idPesquisa) {    
  
    	Empresa empresa = new Empresa(idEmpresa);
    	Pesquisa pesquisa = new Pesquisa(idPesquisa);
    	
    	return service.consultarPorEmpresaAndPesquisa(empresa, pesquisa);
    }
    
    @RequestMapping(value = "/empresa-patriocinadora/{idPesquisa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmpresaPesquisa consultarEmpresaPatriocinadora(@PathVariable(value = "idPesquisa") Long idPesquisa) {    
    	Pesquisa pesquisa = new Pesquisa(idPesquisa);
    	return service.consultarEmpresaPatriocinadora(pesquisa);
    }

}
