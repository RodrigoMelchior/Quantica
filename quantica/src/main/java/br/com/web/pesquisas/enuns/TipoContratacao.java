package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoContratacao {
	CLT("1"), ESTATUTARIO("2"), PESSOA_JURIDICA("3");
	
	private String code;
	 
    private TipoContratacao(String code) {
        this.code = code;
    }
    
    private static final Map<String, TipoContratacao> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoContratacao.values()),
            TipoContratacao::getCode
            );     

    @JsonCreator
    public static TipoContratacao forValue(String code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }	
}
