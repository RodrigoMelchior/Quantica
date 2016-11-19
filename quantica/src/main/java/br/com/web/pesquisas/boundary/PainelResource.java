package br.com.web.pesquisas.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Painel;
import br.com.web.pesquisas.service.PainelService;
import br.com.web.pesquisas.util.MensagemUtil;

@RestController
@CrossOrigin(origins = "http://www.xn--pesquisasderemunerao-7yb1g.com.br")
@RequestMapping(path = "/api/paineis")
public class PainelResource extends EntityServiceBasedRestController<Painel, Long, PainelService>{

    @Autowired
    private MensagemUtil mensagemUtil;

}
