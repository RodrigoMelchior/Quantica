package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum MesDoAno {
	JANEIRO("01"), 
	FEVEREIRO("02"), 
	MARCO("03"), 
	ABRIL("04"), 
	MAIO("05"), 
	JUNHO("06"), 
	JULHO("07"), 
	AGOSTO("08"), 
	SETEMBRO("09"), 
	OUTUBRO("10"), 
	NOVEMBRO("11"), 
	DEZEMBRO("12");
	
	private String code;
	 
    private MesDoAno(String code) {
        this.code = code;
    }
 
    private static final Map<String, MesDoAno> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(MesDoAno.values()),
            MesDoAno::getCode
            );     

    @JsonCreator
    public static MesDoAno forValue(Integer code){
        return LOOKUP.get(code);
    }

    
    @JsonValue
    public String getCode() {
        return code;
    }
}
