package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum NivelEstruturaOrganizacional {
	NIVEL_1("0"), 
	NIVEL_2("1"), 
	NIVEL_3("2"), 
	NIVEL_4("3"), 
	NIVEL_5("4"), 
	NIVEL_6("5"), 
	NIVEL_7("6"), 
	NIVEL_8("7"), 
	NIVEL_9("8"), 
	NIVEL_10("9"), 
	NIVEL_11("10"), 
	NIVEL_12("11"), 
	NIVEL_13("12"), 
	NIVEL_14("13"), 
	NIVEL_15("14");
	
	private String code;
	 
    private NivelEstruturaOrganizacional(String code) {
        this.code = code;
    }
 
    private static final Map<String, NivelEstruturaOrganizacional> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(NivelEstruturaOrganizacional.values()),
            NivelEstruturaOrganizacional::getCode
            );     

    @JsonCreator
    public static NivelEstruturaOrganizacional forValue(Integer code){
        return LOOKUP.get(code);
    }

    
    @JsonValue
    public String getCode() {
        return code;
    }
}
