package br.com.web.pesquisas.enuns;

import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Maps;

public enum NivelEstrutura {
	 NIVEL_1(1), NIVEL_2(2), NIVEL_3(3), NIVEL_4(4), NIVEL_5(5), NIVEL_6(6), NIVEL_7(7), NIVEL_8(8), NIVEL_9(9),
	 NIVEL_10(10), NIVEL_11(11), NIVEL_12(12), NIVEL_13(13), NIVEL_14(14), NIVEL_15(15);
	
	private Integer code;
	 
    private NivelEstrutura(Integer code) {
        this.code = code;
    }
    
    private static final Map<Integer, NivelEstrutura> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(NivelEstrutura.values()),
            NivelEstrutura::getCode
            );     

    @JsonCreator
    public static NivelEstrutura forValue(Integer code){
        return LOOKUP.get(code);
    }
    
    @JsonValue
    public Integer getCode() {
        return code;
    }	
}
