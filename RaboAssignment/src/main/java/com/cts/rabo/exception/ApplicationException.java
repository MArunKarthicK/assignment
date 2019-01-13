package com.cts.rabo.exception;

/**
 * @author 550072
 *
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 572184876529198035L;
	
	private String errorCode;

	private String message;
	
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
	/**
	 * 
	 */
	public ApplicationException() {
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param httpStatus
	 */
	public ApplicationException(String errorCode, String message) {
		super(errorCode+" : "+message);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	
	public ApplicationException(String errorCode, String message, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.message = message;
	}

}
