package com.cooking.FavouriteRecipe.service;

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

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private RecipeModelMapper recipeModelMapper;
	
	
	public List<Recipe> getAllRecipes() {
		List<RecipeEntity> recipesList = recipeRepository.findAll();

		return recipesList.stream().map(list -> recipeModelMapper.convertEntityToModel(list)).collect(Collectors.toList());
	
	}
	
	public RecipeResponse getRecipeById(Long recipeId) throws RecipeNotFoundException {
		Optional<RecipeEntity> recipeEntity =  recipeRepository.findById(recipeId);
		Recipe recipeModel = new Recipe();
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
	
	public RecipeResponse recipeUpdate(Long id, Recipe recipeModel) throws RecipeNotFoundException {
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
	
	public RecipeResponse deleteRecipe(Long recipeId) throws RecipeNotFoundException {
		Optional<RecipeEntity> recipe= recipeRepository.findById(recipeId);
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
