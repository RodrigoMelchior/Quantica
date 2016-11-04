package br.com.web.pesquisas.web.rest.errors;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConstraintValidationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        ErrorDTO dto = new ErrorDTO(ErrorConstants.ERR_VALIDATION);
      
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            dto.add(constraintViolation.getPropertyPath().toString(), constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
      
        return new ResponseEntity<ErrorDTO>(dto, HttpStatus.BAD_REQUEST);
    }    

}
