package com.cooking.FavouriteRecipe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooking.FavouriteRecipe.entity.RecipeEntity;
import com.cooking.FavouriteRecipe.exception.RecipeNotFoundException;
import com.cooking.FavouriteRecipe.exception.ServiceException;
import com.cooking.FavouriteRecipe.model.Recipe;
import com.cooking.FavouriteRecipe.model.RecipeModelMapper;
import com.cooking.FavouriteRecipe.model.RecipeResponse;
import com.cooking.FavouriteRecipe.repository.RecipeRepository;

/**
 * RecipeService implemented to call the
 * database and send the response to controller
 */
@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private RecipeModelMapper recipeModelMapper;
	
	/**
	 * This method is used to get all the recipes
	 * @return This returns list of recipes
	 */
	public List<Recipe> getAllRecipes() {
		List<RecipeEntity> recipesList = new ArrayList<>();
		try {
			recipesList = recipeRepository.findAll();
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		

		return recipesList.stream().map(list -> recipeModelMapper.convertEntityToModel(list)).collect(Collectors.toList());
	
	}
	
	/**
	 * This method is used to get the recipe for particular Id
	 * @param recipeId This is the parameter to get the recipe
	 * @return RecipeResponse This returns recipe details
	 */
	public RecipeResponse getRecipeById(Long recipeId) {
		Optional<RecipeEntity> recipeEntity;
		Recipe recipeModel = new Recipe();
		try {
			recipeEntity =  recipeRepository.findById(recipeId);
		}
		catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		if(recipeEntity.isPresent())
		{
			RecipeEntity recipe = recipeEntity.get();
			recipeModel = recipeModelMapper.convertEntityToModel(recipe);
		}
		else
		{
			throw new RecipeNotFoundException("Recipe not found for the id :: " + recipeId);
		}
		return new RecipeResponse("Recipe Details for the recipeId: "+recipeId,recipeModel);
	}
	
	/**
	 * This method is used to add the recipe
	 * @param recipeModel This is the parameter to add the recipe
	 * @return RecipeResponse This returns the added recipe
	 */
	public RecipeResponse recipeAdd(Recipe recipeModel) {

		RecipeEntity recipe = recipeModelMapper.convertModeltoEntity(recipeModel);
		
		RecipeEntity recipeEntity =  new RecipeEntity();
		try {
			recipeEntity =	recipeRepository.save(recipe);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		Recipe recipeM = recipeModelMapper.convertEntityToModel(recipeEntity);

		return new RecipeResponse("Recipe Saved Successfully",recipeM);
	}
	
	/**
	 * This method is used to update the recipe
	 * @param id This is the first parameter to update the recipe
	 * @param recipeModel This is the second parameter to update the recipe
	 * @return RecipeResponse This returns the updated recipe
	 */
	public RecipeResponse recipeUpdate(Long id, Recipe recipeModel) {
		Optional<RecipeEntity> currentRecipe = recipeRepository.findById(id);
		
		
		if(currentRecipe.isPresent()) {
			RecipeEntity recipe = recipeModelMapper.convertModeltoEntity(recipeModel);
			RecipeEntity recipeUpdate = currentRecipe.get();
			recipeUpdate.setName(recipe.getName());
			recipeUpdate.setVeg(recipe.getVeg());
			recipeUpdate.setInstructions(recipe.getInstructions());
			recipeUpdate.setNoofpeople(recipe.getNoofpeople());
			recipeUpdate.setCreationDateTime(recipe.getCreationDateTime());
			recipeUpdate.setIngredientsList(recipe.getIngredientsList());
			RecipeEntity recipeEntity = new RecipeEntity();
			try {
				recipeEntity = recipeRepository.save(recipeUpdate);
			}
			catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
			
			
			Recipe recipeM = recipeModelMapper.convertEntityToModel(recipeEntity);

			return new RecipeResponse("Recipe Updated Successfully",recipeM);
		}
		else {
			throw new RecipeNotFoundException("Recipe not found to update for the id :: " + id);
		}
		
	}
	
	/**
	 * This method to used to delete the recipe
	 * @param recipeId This is the parameter to delete the recipeId
	 * @return RecipeResponse This returns the deleted message
	 */
	public RecipeResponse deleteRecipe(Long recipeId) {
		Optional<RecipeEntity> recipe;
		try {
			recipe= recipeRepository.findById(recipeId);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		if(recipe.isPresent()) {
			try {
				recipeRepository.deleteById(recipeId);
				return new RecipeResponse("Recipe Deleted Successfully");
			}
			catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}

		}
		else {
			throw new RecipeNotFoundException("Recipe not found to delete for the id :: " + recipeId);
		}
		 
	       		
	}

}
