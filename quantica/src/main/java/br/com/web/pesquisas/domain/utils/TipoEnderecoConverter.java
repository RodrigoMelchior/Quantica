package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoEndereco;

@Converter(autoApply = true)
public class TipoEnderecoConverter implements AttributeConverter<TipoEndereco, String> {

    @Override
    public String convertToDatabaseColumn(TipoEndereco tipoEndereco) {
        
        return tipoEndereco.getCode();
    }

    @Override
    public TipoEndereco convertToEntityAttribute(String tipo) {
        switch (tipo) {
        case "1":
         return TipoEndereco.RESIDENCIAL;
        case "2":
         return TipoEndereco.COMERCIAL;
        default:
         throw new IllegalArgumentException("Unknown value: " + tipo);
        }

    }

}
