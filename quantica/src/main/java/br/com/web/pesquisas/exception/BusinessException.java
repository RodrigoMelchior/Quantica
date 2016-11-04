package br.com.web.pesquisas.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -9058339639648869099L;
	
	private Integer code;
	private String description;
	
	public BusinessException(Integer code, String message) {
		super(message);
		this.code = code;
	}

    public BusinessException(String message) {
        super(message);
    }
	
	
	public BusinessException(Integer code, String message, String description) {
		super(message);
		this.code = code;
		this.description = description;
	}
	
	
	public BusinessException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BusinessException(Integer code, String message,String description, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getDescription(){
		return this.description;
	}

	
}
