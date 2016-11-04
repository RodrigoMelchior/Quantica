package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.NivelEstrutura;

@Converter(autoApply = true)
public class NivelEstruturaConverter implements AttributeConverter<NivelEstrutura, Integer> {

	@Override
    public Integer convertToDatabaseColumn(NivelEstrutura tipo) {
        
        return tipo.getCode();
    }

    @Override
    public NivelEstrutura convertToEntityAttribute(Integer tipo) {
    	if(tipo == null){
    		return null;
    	}
        switch (tipo) {
        case 1:
         return NivelEstrutura.NIVEL_1;
        case 2:
         return NivelEstrutura.NIVEL_2;
        case 3:
            return NivelEstrutura.NIVEL_3;
        case 4:
            return NivelEstrutura.NIVEL_4;
        case 5:
            return NivelEstrutura.NIVEL_5;
        case 6:
            return NivelEstrutura.NIVEL_6;
        case 7:
            return NivelEstrutura.NIVEL_7;
        case 8:
            return NivelEstrutura.NIVEL_8;
        case 9:
            return NivelEstrutura.NIVEL_9;
        case 10:
            return NivelEstrutura.NIVEL_10;
        case 11:
            return NivelEstrutura.NIVEL_11;
        case 12:
            return NivelEstrutura.NIVEL_12;
        case 13:
            return NivelEstrutura.NIVEL_13;
        case 14:
            return NivelEstrutura.NIVEL_14;
        case 15:
            return NivelEstrutura.NIVEL_15;
        default:
         throw new IllegalArgumentException("Unknown value: " + tipo);
        }    }

    }