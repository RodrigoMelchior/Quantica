package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.SimNao;

@Converter(autoApply = true)
public class SimNaoToStringConverter implements AttributeConverter<SimNao, String> {

    @Override
    public String convertToDatabaseColumn(final SimNao tipo) {
    	if (tipo != null){
            return tipo.getCode();
    	}else{
    		return null;
    	}
    }

    @Override
    public SimNao convertToEntityAttribute(final String tipo) {
        if (tipo != null){
        	switch (tipo.toUpperCase()) {
	            case "S":
	                return SimNao.SIM;
	            case "N":
	                return SimNao.NAO;
	            default:
	                throw new IllegalArgumentException("Unknown value: " + tipo);
	        }
    	}else{
    		return null;
    	}
    }
}