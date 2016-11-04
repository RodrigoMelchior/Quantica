package br.com.web.pesquisas.domain.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.web.pesquisas.enuns.TipoArquivo;

@Converter(autoApply = true)
public class TipoArquivoConverter implements AttributeConverter<TipoArquivo, String> {

    @Override
    public String convertToDatabaseColumn(TipoArquivo tipoArquivo) {
        
        return tipoArquivo.getCode();
    }

    @Override
    public TipoArquivo convertToEntityAttribute(String tipo) {
        switch (tipo) {
        case "1":
         return TipoArquivo.RELATORIO;
        case "2":
            return TipoArquivo.CARGA_DADOS_DA_EMPRESA;
        case "3":
            return TipoArquivo.CARGA_DADOS_DA_EMPRESA;
        default:
         throw new IllegalArgumentException("Unknown value: " + tipo);
        }

    }

}
