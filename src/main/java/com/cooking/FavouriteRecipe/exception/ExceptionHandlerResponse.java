package com.cooking.FavouriteRecipe.exception;

import java.util.List;

public class ExceptionHandlerResponse {
    private String message;
    private List<String> exceptionDetails;

	public ExceptionHandlerResponse(String message, List<String> exceptionDetails) {
		super();
		this.message = message;
		this.exceptionDetails = exceptionDetails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getExceptionDetails() {
		return exceptionDetails;
	}
	public void setExceptionDetails(List<String> exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
	
}

