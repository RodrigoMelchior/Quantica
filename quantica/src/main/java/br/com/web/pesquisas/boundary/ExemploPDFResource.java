package br.com.web.pesquisas.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.web.pesquisas.domain.Agrupador;
import br.com.web.pesquisas.service.AgrupadorService;
import br.com.web.pesquisas.util.MensagemUtil;

@RestController
@RequestMapping(path = "/api/exemploPDF")
public class ExemploPDFResource extends EntityServiceBasedRestController<Agrupador, Long, AgrupadorService>{

    @Autowired
    private MensagemUtil mensagemUtil;
    
   /* @RequestMapping(path = "/{id}/pdf", method = RequestMethod.GET)
    public ModelAndView getRelatorioOrdemServico(@PathVariable("id") Long id) {
        Map<String, Object> parameterMap = new HashMap<>();
        List<OrdemServicoPdfDTO> ordemServicos =  new ArrayList<>();
        
        OrdemServico ordemServico = this.service.recuperarOrdemServicoImpressao(id); //findById(id);
        for (OrdemServicoAtividade atividade : ordemServico.getAtividades()) {
            System.out.println(atividade.getQuantidadeEstimada());
        }
        ordemServicos.add(new OrdemServicoPdfDTO(ordemServico));
        JRDataSource dataSource = new JRBeanCollectionDataSource(ordemServicos);
        
        parameterMap.put("datasource", dataSource);
        parameterMap.put("listaAtividades", new ArrayList<>());
        ModelAndView modelAndView = new ModelAndView(ordemServicoReport, parameterMap);
        return modelAndView;
    }*/

}
