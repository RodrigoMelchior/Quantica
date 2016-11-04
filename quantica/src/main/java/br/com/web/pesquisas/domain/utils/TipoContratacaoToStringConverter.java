package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoContratacao;

@Converter(autoApply = true)
public class TipoContratacaoToStringConverter implements AttributeConverter<TipoContratacao, String> {

    @Override
    public String convertToDatabaseColumn(final TipoContratacao tipoContratacao) {
    	if (tipoContratacao != null){
    	    return tipoContratacao.getCode();
    	}else{
	    	return null;
	    }
    }

    @Override
    public TipoContratacao convertToEntityAttribute(final String tipo) {
    	if (tipo != null){
	        switch (tipo.toUpperCase()) {
	            case "1":
	                return TipoContratacao.CLT;
	            case "2":
	                return TipoContratacao.ESTATUTARIO;
	            case "3":
	                return TipoContratacao.PESSOA_JURIDICA;
	            default:
	                throw new IllegalArgumentException("Unknown value: " + tipo);
	        }
	    }else{
	    	return null;
	    }
    }
}