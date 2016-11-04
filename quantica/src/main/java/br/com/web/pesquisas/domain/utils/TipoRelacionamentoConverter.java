package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoRelacionamento;

@Converter(autoApply = true)
public class TipoRelacionamentoConverter implements AttributeConverter<TipoRelacionamento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoRelacionamento enumm) {
        return enumm.getCode();
    }

    @Override
    public TipoRelacionamento convertToEntityAttribute(Integer tipo) {
        switch (tipo) {
        case 1:
         return TipoRelacionamento.PATRIOCINADORA;
        case 2:
         return TipoRelacionamento.PARTICIPANTE;
        default:
         throw new IllegalArgumentException("Unknown value: " + tipo);
        }

    }

}
