package com.cooking.FavouriteRecipe.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.cooking.FavouriteRecipe.entity.IngredientsEntity;
import com.cooking.FavouriteRecipe.entity.RecipeEntity;
import com.cooking.FavouriteRecipe.repository.RecipeRepository;

@Component
public class RecipeDataLoader implements ApplicationRunner{
	
	Logger logger = LoggerFactory.getLogger(RecipeDataLoader.class);
	
	@Autowired
	private RecipeRepository recipeRepository;

    
    public RecipeDataLoader(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Loading the data while starting the application");
		RecipeEntity recipe1= new RecipeEntity();
		recipe1.setName("Pasta");
		recipe1.setNoofpeople(2);
		SimpleDateFormat createDateTime = new SimpleDateFormat("dd‐MM‐yyyy HH:mm");
        Date date = new Date();
		recipe1.setCreationDateTime(createDateTime.format(date));
		recipe1.setVeg(true);
		List<IngredientsEntity> ingredientsList = new ArrayList<>();
		IngredientsEntity ingredient1 = new IngredientsEntity();
		ingredient1.setIngredientsList("salt and 1 pound (450 g) of pasta");
		ingredientsList.add(ingredient1);
		recipe1.setIngredientsList(ingredientsList);
		recipe1.setInstructions(
		"Season the beaten eggs well with salt and pepper. Heat the oil in a non stick frying pan over "+
		"a medium low heat.");
		recipeRepository.save(recipe1);
		
		
	}

}
