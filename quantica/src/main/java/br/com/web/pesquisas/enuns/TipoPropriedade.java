package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoPropriedade {
	EMPRESA_DE_CAPITAL_ABERTO("1"), 
	EMPRESA_DE_CAPITAL_FECHADO("2"), 
	EMPRESA_SEM_FINS_LUCRATIVOS("3"), 
	JOINT_VENTURE("4"), 
	ORGANIZACAO_NAO_GORVENAMENTAL_ONG("5");
	
	private String code;
	 
    private TipoPropriedade(String code) {
        this.code = code;
    }
    
    private static final Map<String, TipoPropriedade> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoPropriedade.values()),
            TipoPropriedade::getCode
            );     

    @JsonCreator
    public static TipoPropriedade forValue(String code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }	
}
