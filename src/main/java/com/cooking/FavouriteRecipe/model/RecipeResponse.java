package com.cooking.FavouriteRecipe.model;


public class RecipeResponse {
	
    private String responseMessage;
    
    private Recipe recipe;
    
    public RecipeResponse(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
		}

	public RecipeResponse(String responseMessage, Recipe recipe) {
		super();
		this.responseMessage = responseMessage;
		this.recipe = recipe;
	}

	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
