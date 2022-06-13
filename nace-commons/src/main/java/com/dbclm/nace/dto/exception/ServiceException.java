package com.dbclm.nace.dto.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;
  
	private String code ;
	private String message ;
	
	public ServiceException() {
		this.code = "400" ;
		this.message = "BadRequest";
		
	}
	
	public ServiceException(String code,String message) {
		this.code = code ;
		this.message = message;
		
	}
	
}
