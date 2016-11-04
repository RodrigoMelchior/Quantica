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

import br.com.web.pesquisas.domain.Cargo;
import br.com.web.pesquisas.domain.specification.CargoSpecification;
import br.com.web.pesquisas.domain.specification.Conjunction;
import br.com.web.pesquisas.enuns.Mensagem;
import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.service.CargoService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.FiltroCargoDTO;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/relatorio")
public class RelatoriosResource extends EntityServiceBasedRestController<Cargo, Long, CargoService>{

    @Autowired
    private MensagemUtil mensagemUtil;

    @RequestMapping(path = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cargo>> pesquisar(@RequestBody FiltroCargoDTO filtro) {
        
        if (StringUtils.isEmpty(filtro.getEmpresa())) {
            throw new BusinessException(mensagemUtil.getMessage(Mensagem.PARAMETRO_PESQUISA_OBRIGATORIO.getKey()));
        }
        
        Conjunction<Cargo> specification = makeSpecification(filtro);
        Page<Cargo> pageResult = null;
        
        pageResult = this.service.findBySpecification(specification, new PageRequest(filtro.getPage() - 1, filtro.getLimit()));
        
        return new ResponseEntity<List<Cargo>>(pageResult.getContent(),
                HeadersUtil.generatePaginationHttpHeaders(pageResult.getTotalElements()), HttpStatus.OK);   
    }


    
    private Conjunction<Cargo> makeSpecification(FiltroCargoDTO filtro) {
        List<Specification<Cargo>> specifications = new ArrayList<Specification<Cargo>>();
        
        if (StringUtils.isNotEmpty(filtro.getEmpresa())) {
            specifications.add(CargoSpecification.igualEmpresa(filtro.getEmpresa()));
        }
        return new Conjunction<Cargo>(specifications);
    }


}
