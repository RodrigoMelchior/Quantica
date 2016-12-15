package br.com.web.pesquisas.web.rest.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class FiltroCaesbDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private List<String> empresa;

    @Getter
    @Setter
    private String tipo;
    
    @Getter
    @Setter
    private String cod;
        
}
