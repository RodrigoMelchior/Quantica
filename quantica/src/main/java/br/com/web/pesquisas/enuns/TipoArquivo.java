package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum TipoArquivo {
	RELATORIO("1"), CARGA_DADOS_DA_EMPRESA("2"), CARGA_ESTRUTURA_DE_CARGOS("3");
	
	private String code;
	 
    private TipoArquivo(String code) {
        this.code = code;
    }
    
    private static final Map<String, TipoArquivo> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(TipoArquivo.values()),
            TipoArquivo::getCode
            );     

    @JsonCreator
    public static TipoArquivo forValue(String code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public String getCode() {
        return code;
    }	
}
