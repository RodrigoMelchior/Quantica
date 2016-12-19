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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Pergunta;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.PerguntaSpecification;
import br.com.web.pesquisas.enuns.Mensagem;
import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.service.PerguntaService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroPerguntaDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin(origins = "http://www.xn--pesquisasderemunerao-7yb1g.com.br")
@RequestMapping(path = "/api/respostas")
public class RespostaResource extends EntityServiceBasedRestController<Pergunta, Long, PerguntaService>{

    @Autowired
    private MensagemUtil mensagemUtil;

    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pergunta>> pesquisar(@RequestBody FiltroPerguntaDTO filtro) {
        
        if (StringUtils.isEmpty(filtro.getEnunciado())) {
            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
        }
        
        Conjunction<Pergunta> specification = makeSpecification(filtro);
        Page<Pergunta> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        return new ResponseEntity<List<Pergunta>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }

    private Conjunction<Pergunta> makeSpecification(FiltroPerguntaDTO filtro) {
        List<Specification<Pergunta>> specifications = new ArrayList<Specification<Pergunta>>();
        
        if (StringUtils.isNotEmpty(filtro.getEnunciado())) {
            specifications.add(PerguntaSpecification.likeNome(filtro.getEnunciado()));
        }
        
        return new Conjunction<Pergunta>(specifications);
    }    
}
