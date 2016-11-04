package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum SimNao {
	SIM("S"), NAO("N");
	
	private String code;
	 
    private SimNao(String code) {
        this.code = code;
    }
 
    private static final Map<String, SimNao> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(SimNao.values()),
            SimNao::getCode
            );     

    @JsonCreator
    public static SimNao forValue(Integer code){
        return LOOKUP.get(code);
    }

    
    @JsonValue
    public String getCode() {
        return code;
    }
}
