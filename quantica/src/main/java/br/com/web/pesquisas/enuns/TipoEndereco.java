package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoEndereco {
	RESIDENCIAL("1"), COMERCIAL("2");
	
	private String code;
	 
    private TipoEndereco(String code) {
        this.code = code;
    }
 
    private static final Map<String, TipoEndereco> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoEndereco.values()),
            TipoEndereco::getCode
            );     

    @JsonCreator
    public static TipoEndereco forValue(String code){
        return LOOKUP.get(code);
    }

    
    @JsonValue
    public String getCode() {
        return code;
    }
}
