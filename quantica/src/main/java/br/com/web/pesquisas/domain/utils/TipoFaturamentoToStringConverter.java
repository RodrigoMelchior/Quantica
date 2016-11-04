package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoFaturamento;

@Converter(autoApply = true)
public class TipoFaturamentoToStringConverter implements AttributeConverter<TipoFaturamento, String> {

    @Override
    public String convertToDatabaseColumn(final TipoFaturamento tipoFaturamento) {
    	if (tipoFaturamento != null){
            return tipoFaturamento.getCode();
    	}else{
    		return null;
    	}
    }

    @Override
    public TipoFaturamento convertToEntityAttribute(final String tipo) {
        if (tipo != null){
        	switch (tipo.toUpperCase()) {
	            case "1":
	                return TipoFaturamento.PEQUENA;
	            case "2":
	                return TipoFaturamento.MEDIA;
	            case "3":
	                return TipoFaturamento.GRANDE;
	            default:
	                throw new IllegalArgumentException("Unknown value: " + tipo);
	        }
    	}else{
    		return null;
    	}
    }
}