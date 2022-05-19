package com.cooking.FavouriteRecipe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ingredients")
public class IngredientsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long ingredientId;

	@NotBlank(message="Ingredients must not be empty")
	private String ingredient;

	public IngredientsEntity() {
		super();
	}
	
	public IngredientsEntity(Long ingredientId, String ingredient) {
		this.ingredientId = ingredientId;
		this.ingredient = ingredient;
	}
	
	public Long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredientsList(String ingredient) {
		this.ingredient = ingredient;
	}
	
	@Override
	public String toString() {
		return "IngredientsEntity [ingredientId=" + ingredientId + ", ingredient=" + ingredient + "]";
	}
	
}

