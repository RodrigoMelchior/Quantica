package br.com.web.pesquisas.boundary;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Pesquisa;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.PesquisaSpecification;
import br.com.web.pesquisas.service.PesquisaService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroPesquisaDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/pesquisas")
public class PesquisaResource extends EntityServiceBasedRestController<Pesquisa, Long, PesquisaService>{

    @Autowired
    private MensagemUtil mensagemUtil;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/pesquisa-por-empresa/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pesquisa> consultarPesquisaPorEmpresa(@PathVariable(value = "id") Long id) {    
  
    	List<Pesquisa> pesquisas = service.consultarPesquisaPorEmpresa(id);
        if (pesquisas != null) {
            return new ResponseEntity(pesquisas, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pesquisa>> pesquisar(@RequestBody FiltroPesquisaDTO filtro) {
        
    	String nome = filtro.getNome();
        if (StringUtils.isEmpty(filtro.getNome())) {
        	nome = "";
//            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
        }
        
        filtro.setNome(nome + "%");
        
        Conjunction<Pesquisa> specification = makeSpecification(filtro);
        Page<Pesquisa> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        return new ResponseEntity<List<Pesquisa>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }
    
   private Conjunction<Pesquisa> makeSpecification(FiltroPesquisaDTO filtro) {
	    List<Specification<Pesquisa>> specifications = new ArrayList<Specification<Pesquisa>>();
	    
	    if (StringUtils.isNotEmpty(filtro.getNome())) {
	        specifications.add(PesquisaSpecification.nome(filtro.getNome()));
	    }
	    if (filtro.getDataInicio() != null) {
	        specifications.add(PesquisaSpecification.menorDataInicio(filtro.getDataInicio()));
	        specifications.add(PesquisaSpecification.maiorDataFim(filtro.getDataInicio()));
	    }
	    if (filtro.getDataFim() != null) {
	        specifications.add(PesquisaSpecification.maiorDataInicio(filtro.getDataFim()));
	        specifications.add(PesquisaSpecification.menorDataInicio(filtro.getDataFim()));
	    }
	    return new Conjunction<Pesquisa>(specifications);
	}


}
