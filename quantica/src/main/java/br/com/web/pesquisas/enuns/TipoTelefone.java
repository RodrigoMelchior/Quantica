package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoTelefone {
	RESIDENCIAL("1"), COMERCIAL("2"), CELULAR("3"), CELULAR_CORPORATIVO("4"), PESSOAL("5");
	
	private String code;
	 
    private TipoTelefone(String code) {
        this.code = code;
    }
    
    private static final Map<String, TipoTelefone> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoTelefone.values()),
            TipoTelefone::getCode
            );     

    @JsonCreator
    public static TipoTelefone forValue(String code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }	
}
