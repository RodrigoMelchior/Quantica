package br.com.web.pesquisas.web.rest.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class PesquisaDTO extends PaginacaoDTO{

    private static final long serialVersionUID = 2765980336298047301L;
    @Getter 
    @Setter
	private Long id;
    
    @Getter 
    @Setter
	private String nome;
	
	@Getter 
	@Setter
	private String habilitarRelatorio;

	@Getter 
	@Setter
	private Date inicio;

	@Getter 
	@Setter
	private Date termino;
        
    
}
