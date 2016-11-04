package br.com.web.pesquisas.web.rest.dto;

import java.util.List;

import br.com.web.pesquisas.domain.PerguntaPesquisa;
import lombok.Getter;
import lombok.Setter;

public class PerguntaPesquisaDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private Long idEmpresa;

    @Getter
    @Setter
    private List<PerguntaPesquisa> perguntaPesquisas;
        
    
}
