package com.cooking.FavouriteRecipe.exception;


public class ExceptionResponse {
    private String message;
    private String exceptionMessage;

	public ExceptionResponse(String message, String exceptionMessage) {
		super();
		this.message = message;
		this.exceptionMessage = exceptionMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + ", exceptionMessage=" + exceptionMessage + "]";
	}
	
}
