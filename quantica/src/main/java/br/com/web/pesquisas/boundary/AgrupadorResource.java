package br.com.web.pesquisas.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Agrupador;
import br.com.web.pesquisas.service.AgrupadorService;
import br.com.web.pesquisas.util.MensagemUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(path = "/api/agrupadores")
public class AgrupadorResource extends EntityServiceBasedRestController<Agrupador, Long, AgrupadorService>{

    @Autowired
    private MensagemUtil mensagemUtil;

}
