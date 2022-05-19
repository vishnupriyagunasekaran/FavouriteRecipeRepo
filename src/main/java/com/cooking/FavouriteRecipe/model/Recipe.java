package com.cooking.FavouriteRecipe.model;

import java.util.List;



public class Recipe {

	
	private Long id;
	private String name;
	private boolean veg; 
	private String creationDateTime;
	private int noofpeople;
	private String instructions;
	private List<String> ingredientsList;
	
	public Recipe()
	{
		super();
	}


	public Recipe(Long id,String name, boolean veg, String creationDateTime, String instructions, List<String> ingredientsList, int noofpeople) {
		this.id = id;
		this.name = name;
		this.veg = veg;
		this.creationDateTime = creationDateTime;
		this.instructions = instructions;
		this.ingredientsList = ingredientsList;
		this.noofpeople = noofpeople;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getVeg() {
		return veg;
	}
	public void setVeg(boolean veg) {
		this.veg = veg;
	}
	public String getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(String creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	public int getNoofpeople() {
		return noofpeople;
	}
	public void setNoofpeople(int noofpeople) {
		this.noofpeople = noofpeople;
	}

	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<String> getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(List<String> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}


	@Override
	public String toString() {
		return "Recipe [recipeId=" + id + ", name=" + name + ", veg=" + veg + ", creationDateTime="
				+ creationDateTime + ", noofpeople=" + noofpeople + ", ingredients=" + ingredientsList
				+ ", instructions=" + instructions + "]";
	}

}
