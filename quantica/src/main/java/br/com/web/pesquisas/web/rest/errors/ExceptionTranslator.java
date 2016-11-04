package br.com.web.pesquisas.web.rest.errors;

import java.util.Locale;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.web.pesquisas.exception.BusinessException;
import br.com.web.pesquisas.exception.NotFoundException;
import br.com.web.pesquisas.web.rest.util.HeadersUtil;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler{

	
	private MessageSource messageSource;
	
	@Autowired
	public ExceptionTranslator(MessageSource messageSource){
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDTO> processDataIntegrityViolationException(DataIntegrityViolationException ex){
		 
        String constraintName = null;
        if ((ex.getCause() != null) && (ex.getCause() instanceof ConstraintViolationException)) {
            constraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName();
        }

        String message = messageSource.getMessage(constraintName, null, Locale.getDefault());
        if (message != null) {
        	ErrorDTO error = new ErrorDTO(message);
        	return new ResponseEntity<ErrorDTO>(error,
    				HeadersUtil.generateBusinessHttpHeaders(), HttpStatus.CONFLICT);
        } else {
        	return new ResponseEntity<ErrorDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        } 

	}
	
    
	
	@ExceptionHandler(ConcurrencyFailureException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorDTO processConcurencyError(ConcurrencyFailureException ex) {
		return new ErrorDTO(ErrorConstants.ERR_CONCURRENCY_FAILURE);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> processBusinessError(BusinessException ex) {
		
		ErrorDTO error = new ErrorDTO(ex.getCode(), ex.getMessage(), ex.getDescription());
		return new ResponseEntity<ErrorDTO>(error,
				HeadersUtil.generateBusinessHttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> processNotFoundException(NotFoundException ex) {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
	
	
	
	@ExceptionHandler(CustomParameterizedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ParameterizedErrorDTO processParameterizedValidationError(CustomParameterizedException ex) {
		return ex.getErrorDTO();
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ErrorDTO processAccessDeniedExcpetion(AccessDeniedException e) {
		return new ErrorDTO(ErrorConstants.ERR_ACCESS_DENIED, e.getMessage());
	}


}
