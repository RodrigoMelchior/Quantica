package br.com.web.pesquisas.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MensagemUtil {

    @Autowired
    MessageSource messageSource;
    
    public String getMessage(String key){
        return messageSource.getMessage(key, null,Locale.getDefault());
    }
}
