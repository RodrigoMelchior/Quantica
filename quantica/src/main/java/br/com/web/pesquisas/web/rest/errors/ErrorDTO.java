package br.com.web.pesquisas.web.rest.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer code;
    private final String message;
    private final String description;

    private List<FieldErrorDTO> fieldErrors;

    ErrorDTO(String message) {
        this(null, message, null, null);
    }

    ErrorDTO(Integer code, String message) {
        this(code, message, null, null);
    }
    
    ErrorDTO(String message, String description) {
    	this(null, message, description, null);
    }

    ErrorDTO(Integer code, String message, String description) {
        this(null, message, description, null);
    }    
    
    ErrorDTO(String message, String description, List<FieldErrorDTO> fieldErrors) {
    	this(null, message, description, fieldErrors);
    }

    ErrorDTO(Integer code, String message, String description, List<FieldErrorDTO> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
        this.code =  code;
    }
    
    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDTO(objectName, field, message));
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
}
