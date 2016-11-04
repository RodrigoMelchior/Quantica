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

import br.com.web.pesquisas.domain.Item;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.domain.specification.ItemSpecification;
import br.com.web.pesquisas.enuns.Mensagem;
import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.service.ItemService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroItemDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/itens")
public class ItemResource extends EntityServiceBasedRestController<Item, Long, ItemService>{

    @Autowired
    private MensagemUtil mensagemUtil;

    
    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> pesquisar(@RequestBody FiltroItemDTO filtro) {
        
    	String nome = filtro.getNome();
        if (StringUtils.isEmpty(filtro.getNome())) {
        	nome = "";
//            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
        }
        
        filtro.setNome(nome + "%");
        
        Conjunction<Item> specification = makeSpecification(filtro);
        Page<Item> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        return new ResponseEntity<List<Item>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }


    
    private Conjunction<Item> makeSpecification(FiltroItemDTO filtro) {
        List<Specification<Item>> specifications = new ArrayList<Specification<Item>>();
        
        if (StringUtils.isNotEmpty(filtro.getNome())) {
            specifications.add(ItemSpecification.likeNome(filtro.getNome()));
        }

        if (StringUtils.isNotEmpty(filtro.getDescricao())) {
            specifications.add(ItemSpecification.descricao(filtro.getDescricao()));
        }
        return new Conjunction<Item>(specifications);
    }

}
