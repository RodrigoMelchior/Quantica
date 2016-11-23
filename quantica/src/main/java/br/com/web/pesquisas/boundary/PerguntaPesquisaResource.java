package br.com.web.pesquisas.boundary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Empresa;
import br.com.web.pesquisas.domain.PerguntaPesquisa;
import br.com.web.pesquisas.domain.Resposta;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.PerguntaPesquisaSpecification;
import br.com.web.pesquisas.enuns.Mensagem;
import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.service.PerguntaPesquisaService;
import br.com.web.pesquisas.service.RespostaService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroPerguntaPesquisaDTO;
import br.com.web.pesquisas.web.rest.dto.PerguntaPesquisaDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/perguntas/pesquisa")
public class PerguntaPesquisaResource extends EntityServiceBasedRestController<PerguntaPesquisa, Long, PerguntaPesquisaService>{

    @Autowired
    private MensagemUtil mensagemUtil;
    
    @Autowired
    private RespostaService respostaService;

    
    @RequestMapping(path = "/salvar-resposta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PerguntaPesquisa>> salvar(@RequestBody PerguntaPesquisaDTO perguntaPesquisas) {
    	try {
    		for (PerguntaPesquisa perguntaPesquisa : perguntaPesquisas.getPerguntaPesquisas()) {
    			Resposta resposta = perguntaPesquisa.getResposta();
    			resposta.setPergunta(perguntaPesquisa);
    			resposta.setEmpresa(new Empresa(perguntaPesquisas.getIdEmpresa()));
    			respostaService.create(resposta);
    		}
    		return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
    }

    
    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PerguntaPesquisa>> pesquisar(@RequestBody FiltroPerguntaPesquisaDTO filtro) {
        
        if (filtro.getPesquisa() == null) {
            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
        }
        
        Conjunction<PerguntaPesquisa> specification = makeSpecification(filtro);
        Page<PerguntaPesquisa> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        List<Resposta> respostas = null;
        Empresa empresa = new Empresa();
        empresa.setId( new Long(1));
        
        for (PerguntaPesquisa perguntaPesquisa : pageResult) {
        	respostas = respostaService.findByPerguntaAndEmpresa(perguntaPesquisa, empresa);
        	if (respostas.size() > 0){
        		perguntaPesquisa.setResposta(respostas.get(0));
        	}
		}
        
        return new ResponseEntity<List<PerguntaPesquisa>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }

    private Conjunction<PerguntaPesquisa> makeSpecification(FiltroPerguntaPesquisaDTO filtro) {
        List<Specification<PerguntaPesquisa>> specifications = new ArrayList<Specification<PerguntaPesquisa>>();
        
        if (filtro.getPesquisa() != null) {
            specifications.add(PerguntaPesquisaSpecification.equalPesquisa(filtro.getPesquisa()));
        }
        
        return new Conjunction<PerguntaPesquisa>(specifications);
    }    
}
