package br.com.web.pesquisas.boundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import br.com.web.pesquisas.domain.Agrupador;
import br.com.web.pesquisas.service.AgrupadorService;
import br.com.web.pesquisas.util.MensagemUtil;
import br.com.web.pesquisas.web.rest.dto.OrdemServicoPdfDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RestController
@CrossOrigin(origins = "http://www.xn--pesquisasderemunerao-7yb1g.com.br")
@RequestMapping(path = "/api/exemploPDF")
public class ExemploPDFResource extends EntityServiceBasedRestController<Agrupador, Long, AgrupadorService>{

    @Autowired
    private MensagemUtil mensagemUtil;
    
    @Autowired
    @Qualifier("ordemServicoReport")
    private JasperReportsPdfView ordemServicoReport;
    
    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public ModelAndView getRelatorioOrdemServico() {
        Map<String, Object> parameterMap = new HashMap<>();
        List<OrdemServicoPdfDTO> ordemServicos =  new ArrayList<>();
        
        /*OrdemServico ordemServico = this.service.recuperarOrdemServicoImpressao(id); //findById(id);
        for (OrdemServicoAtividade atividade : ordemServico.getAtividades()) {
            System.out.println(atividade.getQuantidadeEstimada());
        }
        ordemServicos.add(new OrdemServicoPdfDTO(ordemServico));*/
        JRDataSource dataSource = new JRBeanCollectionDataSource(ordemServicos);
        
        parameterMap.put("datasource", dataSource);
        parameterMap.put("listaAtividades", new ArrayList<>());
        ModelAndView modelAndView = new ModelAndView(ordemServicoReport, parameterMap);
        return modelAndView;
    }

}
