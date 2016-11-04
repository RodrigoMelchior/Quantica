package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoRelacionamento {
	PATRIOCINADORA(1), PARTICIPANTE(2);
	
	private Integer code;
	 
    private TipoRelacionamento(Integer code) {
        this.code = code;
    }
    
    private static final Map<Integer, TipoRelacionamento> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoRelacionamento.values()),
            TipoRelacionamento::getCode
            );     

    @JsonCreator
    public static TipoRelacionamento forValue(Integer code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public Integer getCode() {
        return code;
    }	
}
