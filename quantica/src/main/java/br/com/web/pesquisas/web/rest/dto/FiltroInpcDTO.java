package br.com.web.pesquisas.web.rest.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class FiltroInpcDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private Date mesAnoInicio;
    
    @Getter
    @Setter
    private Date mesAnoFim;
    
}
