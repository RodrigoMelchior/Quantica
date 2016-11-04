package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoFaturamento {
	PEQUENA("1"), MEDIA("2"), GRANDE("3");
	
	private String code;
	 
    private TipoFaturamento(String code) {
        this.code = code;
    }
    
    private static final Map<String, TipoFaturamento> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoFaturamento.values()),
            TipoFaturamento::getCode
            );     

    @JsonCreator
    public static TipoFaturamento forValue(String code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }	
}
