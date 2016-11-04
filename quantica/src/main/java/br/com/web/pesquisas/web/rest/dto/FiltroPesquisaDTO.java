package br.com.web.pesquisas.web.rest.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class FiltroPesquisaDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private String nome;
    
    @Getter
    @Setter
    private Date dataInicio;
    
    @Getter
    @Setter
    private Date dataFim;
        
}
