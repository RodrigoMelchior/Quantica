package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.NivelEstruturaOrganizacional;

@Converter(autoApply = true)
public class NivelToStringConverter implements AttributeConverter<NivelEstruturaOrganizacional, String> {

    @Override
    public String convertToDatabaseColumn(final NivelEstruturaOrganizacional nivel) {
        if (nivel != null){
        	return nivel.getCode();        	
        }else{
        	return null;
        }
    }

    @Override
    public NivelEstruturaOrganizacional convertToEntityAttribute(final String nivel) {
    	if (nivel != null){
	        switch (nivel.toUpperCase()) {
	            case "0":
	                return NivelEstruturaOrganizacional.NIVEL_1;
	            case "1":
	                return NivelEstruturaOrganizacional.NIVEL_2;
	            case "2":
	                return NivelEstruturaOrganizacional.NIVEL_3;
	            case "3":
	                return NivelEstruturaOrganizacional.NIVEL_4;
	            case "4":
	                return NivelEstruturaOrganizacional.NIVEL_5;
	            case "5":
	                return NivelEstruturaOrganizacional.NIVEL_6;
	            case "6":
	                return NivelEstruturaOrganizacional.NIVEL_7;
	            case "7":
	                return NivelEstruturaOrganizacional.NIVEL_8;
	            case "8":
	                return NivelEstruturaOrganizacional.NIVEL_9;
	            case "9":
	                return NivelEstruturaOrganizacional.NIVEL_10;
	            case "10":
	                return NivelEstruturaOrganizacional.NIVEL_11;
	            case "11":
	                return NivelEstruturaOrganizacional.NIVEL_12;
	            case "12":
	                return NivelEstruturaOrganizacional.NIVEL_13;
	            case "13":
	                return NivelEstruturaOrganizacional.NIVEL_14;
	            case "14":
	                return NivelEstruturaOrganizacional.NIVEL_15;
	            default:
	                throw new IllegalArgumentException("Unknown value: " + nivel);
	        }
    	}else{
    		return null;
    	}
    }
}