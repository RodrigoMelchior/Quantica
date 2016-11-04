package br.com.web.pesquisas.web.rest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PaginacaoDTO implements Serializable{

    private static final long serialVersionUID = 2765980336298047301L;

    @Getter
    @Setter
    private Integer page;
    
    @Getter
    @Setter
    private Integer limit;
    
    @Getter
    @Setter
    private String orderBy;
    
}
