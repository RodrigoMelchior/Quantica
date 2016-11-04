package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoTelefone;

@Converter(autoApply = true)
public class TipoTelefoneConverter implements AttributeConverter<TipoTelefone, String> {

    @Override
    public String convertToDatabaseColumn(TipoTelefone tipo) {
        
        return tipo.getCode();
    }

    @Override
    public TipoTelefone convertToEntityAttribute(String tipo) {
        switch (tipo) {
        case "1":
         return TipoTelefone.RESIDENCIAL;
        case "2":
         return TipoTelefone.COMERCIAL;
        case "3":
            return TipoTelefone.CELULAR;
        case "4":
            return TipoTelefone.CELULAR_CORPORATIVO;
        case "5":
            return TipoTelefone.PESSOAL;
        default:
         throw new IllegalArgumentException("Unknown value: " + tipo);
        }    }

}
