package com.cooking.FavouriteRecipe.exception;

public class ServiceException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4296613671998237837L;
	private String errorMessage;

	public ServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	  
	  
	  
}
