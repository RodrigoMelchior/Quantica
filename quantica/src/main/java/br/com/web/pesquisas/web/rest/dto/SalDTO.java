package br.com.web.pesquisas.web.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class SalDTO implements Serializable{

    private static final long serialVersionUID = 2765980336298047301L;
    @Getter 
    @Setter
	private BigDecimal sal1;
    
    @Getter 
    @Setter
	private BigDecimal sal2;
	
	@Getter 
	@Setter
	private BigDecimal sal3;


	public SalDTO(BigDecimal sal1, BigDecimal sal2, BigDecimal sal3) {
		super();
		this.sal1 = sal1;
		this.sal2 = sal2;
		this.sal3 = sal3;
	}

	public SalDTO(Object[] sal) {
		super();
		this.sal1 = new BigDecimal(sal[0].toString());
		this.sal2 = new BigDecimal(sal[1].toString());
		this.sal3 = new BigDecimal(sal[2].toString());
	}
	
	public SalDTO() {
		super();
	}
        
    
}
