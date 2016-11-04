package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoPropriedade;

@Converter(autoApply = true)
public class TipoPropriedadeToStringConverter implements AttributeConverter<TipoPropriedade, String> {

    @Override
    public String convertToDatabaseColumn(final TipoPropriedade tipoPropriedade) {
    	if (tipoPropriedade != null){
    	    return tipoPropriedade.getCode();
    	}else{
    		return null;
    	}
    }

    @Override
    public TipoPropriedade convertToEntityAttribute(final String tipo) {
    	if (tipo != null){
	        switch (tipo.toUpperCase()) {
	            case "1":
	                return TipoPropriedade.EMPRESA_DE_CAPITAL_ABERTO;
	            case "2":
	                return TipoPropriedade.EMPRESA_DE_CAPITAL_FECHADO;
	            case "3":
	                return TipoPropriedade.EMPRESA_SEM_FINS_LUCRATIVOS;
	            case "4":
	                return TipoPropriedade.JOINT_VENTURE;
	            case "5":
	                return TipoPropriedade.ORGANIZACAO_NAO_GORVENAMENTAL_ONG;
	            default:
	                throw new IllegalArgumentException("Unknown value: " + tipo);
	        }
    	}else{
    		return null;
    	}
    }
}