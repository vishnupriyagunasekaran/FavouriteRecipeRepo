package com.cooking.FavouriteRecipe.exception;

public class ForbiddenResponse {
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ForbiddenResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "{\n\"errorMessage\":" + errorMessage + "\n}";
	}
	
	
    
	
}
