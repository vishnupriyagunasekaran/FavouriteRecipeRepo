package com.cooking.FavouriteRecipe.exception;


public class RecipeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2998401851832401248L;
	
	private String exceptionMessage;

	public RecipeNotFoundException(String exceptionMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
}
