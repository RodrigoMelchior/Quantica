package br.com.web.pesquisas.web.rest.dto;

import lombok.Getter;
import lombok.Setter;

public class FiltroEmpresaDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private String nome;
        
}
