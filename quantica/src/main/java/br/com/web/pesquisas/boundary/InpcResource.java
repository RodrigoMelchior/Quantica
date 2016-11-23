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

import br.com.web.pesquisas.domain.Inpc;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.InpcSpecification;
import br.com.web.pesquisas.enuns.Mensagem;
import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.service.InpcService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroInpcDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/inpc")
public class InpcResource extends EntityServiceBasedRestController<Inpc, Long, InpcService>{

    @Autowired
    private MensagemUtil mensagemUtil;

    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Inpc>> pesquisar(@RequestBody FiltroInpcDTO filtro) {
        
//        if (filtro.getMesAnoInicio() == null && filtro.getMesAnoFim() == null) {
//            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
//        }
        
        Conjunction<Inpc> specification = makeSpecification(filtro);
        Page<Inpc> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        return new ResponseEntity<List<Inpc>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }


    
    private Conjunction<Inpc> makeSpecification(FiltroInpcDTO filtro) {
        List<Specification<Inpc>> specifications = new ArrayList<Specification<Inpc>>();
        
        boolean nenhum = true;
        
        if (filtro.getMesAnoInicio() != null && filtro.getMesAnoFim() != null) {
            specifications.add(InpcSpecification.dataInicioFim(filtro.getMesAnoInicio(), filtro.getMesAnoFim()));
            nenhum = false;
        }else if (filtro.getMesAnoInicio() != null) {
            specifications.add(InpcSpecification.dataInicio(filtro.getMesAnoInicio()));            
            nenhum = false;
        }else if (filtro.getMesAnoFim() != null) {
            specifications.add(InpcSpecification.dataFim(filtro.getMesAnoFim()));
            nenhum = false;
        }
        
        if (nenhum){
        	specifications.add(InpcSpecification.valor());
        }
        
        return new Conjunction<Inpc>(specifications);
    }

}
