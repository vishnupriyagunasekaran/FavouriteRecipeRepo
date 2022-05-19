package com.cooking.FavouriteRecipe.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity
@Table(name = "recipe")
public class RecipeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Name should not be empty")
	private String name;
	
	@NotNull
	private boolean veg;
	
	private String creationDateTime;
	
	@NotNull
	@Min(value=1,message="Servings must be greater than or equal to 1")
    @Max(value=15,message="Servings must be less than or equal to 15")
	private int noofpeople;
	
	@Column(length = 5000)
	@NotBlank(message="Instructions must not be empty")
	private String instructions;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="rc_fid", referencedColumnName = "id")
	private List<IngredientsEntity> ingredientsList;

	public RecipeEntity(){
		super();
	}

	public RecipeEntity(String name, boolean veg, String creationDateTime, String instructions, List<IngredientsEntity> ingredientsList, int noofpeople) {
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

	public List<IngredientsEntity> getIngredientsList() {
		return ingredientsList;
	}

	public void setIngredientsList(List<IngredientsEntity> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}


	@Override
	public String toString() {
		return "Recipe [recipeId=" + id + ", name=" + name + ", veg=" + veg + ", creationDateTime="
				+ creationDateTime + ", noofpeople=" + noofpeople + ", ingredients=" + ingredientsList
				+ ", instructions=" + instructions + "]";
	}

}
