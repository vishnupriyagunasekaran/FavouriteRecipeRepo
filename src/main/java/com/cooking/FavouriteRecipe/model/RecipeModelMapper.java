package com.cooking.FavouriteRecipe.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cooking.FavouriteRecipe.entity.IngredientsEntity;
import com.cooking.FavouriteRecipe.entity.RecipeEntity;

@Component
public class RecipeModelMapper {
	

    public RecipeEntity convertModeltoEntity(Recipe recipeModel) {
        SimpleDateFormat CREATE_DATE_FORMAT = new SimpleDateFormat("dd‐MM‐yyyy HH:mm");
        Date date = new Date();
        ModelMapper modelMapper = new ModelMapper();
        RecipeEntity recipeEntity = modelMapper.map(recipeModel, RecipeEntity.class);
        
        List<IngredientsEntity> ingredientsList =  recipeModel.getIngredientsList().stream().map( i ->
        {
        	IngredientsEntity ie = new IngredientsEntity();
        	ie.setIngredientsList(i);
        	return ie;
        }
        ).collect(Collectors.toList());
       
        recipeEntity.setIngredientsList(ingredientsList);
        recipeEntity.setCreationDateTime(CREATE_DATE_FORMAT.format(date));

        return recipeEntity;
    }

    public Recipe convertEntityToModel(RecipeEntity recipeEntity) {

    	SimpleDateFormat createDateTime = new SimpleDateFormat("dd‐MM‐yyyy HH:mm");
	    Date date = new Date();
		recipeEntity.setCreationDateTime(createDateTime.format(date));
		
		ModelMapper modelMapper = new ModelMapper();
		Recipe recipeModel = modelMapper.map(recipeEntity, Recipe.class);
		List<IngredientsEntity> ingredients = recipeEntity.getIngredientsList();
		List<String> ingredient = ingredients.stream().map(i -> i.getIngredient()).
                collect(Collectors.toList());
		recipeModel.setIngredientsList(ingredient);
		
		return recipeModel;
        
    }
}


