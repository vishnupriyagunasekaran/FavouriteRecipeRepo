package com.cooking.FavouriteRecipe.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.cooking.FavouriteRecipe.entity.RecipeEntity;
import com.cooking.FavouriteRecipe.exception.RecipeNotFoundException;
import com.cooking.FavouriteRecipe.model.Recipe;
import com.cooking.FavouriteRecipe.model.RecipeModelMapper;
import com.cooking.FavouriteRecipe.model.RecipeResponse;
import com.cooking.FavouriteRecipe.repository.RecipeRepository;
import com.cooking.FavouriteRecipe.service.RecipeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceTest {
	
	@Autowired
	@InjectMocks
	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepository;

	Recipe model;
	Optional<RecipeEntity> optionalRecipe;
	RecipeEntity recipeEntity;

	@BeforeEach
	public void setup(){
		
		List<String> ingredientsList = new ArrayList<>();
		ingredientsList.add("salt and 1 pound (450 g) of pasta");
		model = new Recipe(2l,"pasta", true, "19‐05‐2022 09:39", "Season the beaten eggs well with salt and pepper",
                ingredientsList, 2);
		
		RecipeModelMapper modelMapper = new RecipeModelMapper();
		recipeEntity = modelMapper.convertModeltoEntity(model);
		optionalRecipe = Optional.of(recipeEntity);
		
		ReflectionTestUtils.setField(recipeService, "recipeRepository", recipeRepository);
    	
	}

	@Test
	@DisplayName("Given RecipeId, when the request is valid, then get recipe detail")
	public void givenRequest_ForRecipe_thenReturn200() throws RecipeNotFoundException {

		
        Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe);
        RecipeResponse recipeResponse = recipeService.getRecipeById(2l);
		assertNotNull(recipeResponse.getRecipe());
		assertEquals(recipeResponse.getRecipe().getName(), recipeResponse.getRecipe().getName());

	}
	
	@Test
	@DisplayName("Given Request, when the request is valid, then get all the recipes details")
	public void givenRequest_ForRecipes_thenReturn200() throws RecipeNotFoundException {

		List<RecipeEntity> recipeList = new ArrayList<>();
		recipeList.add(recipeEntity);
		Mockito.when(recipeRepository.findAll()).thenReturn(recipeList);
        List<Recipe> recipe = recipeService.getAllRecipes();
		assertNotNull(recipe);
		

	}

	@Test
	@DisplayName("Given RecipeId and recipe detail, when the request is valid, then update and validate the data")
	public void givenRequest_ToUpdate_thenReturn200() throws RecipeNotFoundException {

		model.setNoofpeople(6);
		Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe);
		Mockito.when(recipeRepository.save(any())).thenReturn(recipeEntity);
		RecipeResponse recipe = recipeService.recipeUpdate(2l, model);
		assertNotNull(recipe);
		assertEquals(recipe.getRecipe().getNoofpeople(), model.getNoofpeople());

	}

	@Test
	@DisplayName("Given RecipeId, when the request is valid, then delete and validate the data")
	public void givenRequest_ToDelete_thenReturn200() throws RecipeNotFoundException {
		
		Optional<RecipeEntity> optionalRecipe1 = Optional.empty();
		Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe);
		recipeService.deleteRecipe(2l);
		Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe1);
		assertFalse(recipeRepository.findById(1l).isPresent());
		
	}
	
	@Test
	@DisplayName("Given Invalid RecipeId, when the request needs to get the data, then throw RecipeNotFoundException")
	public void getAllRecipesTest_Negative() throws RecipeNotFoundException {
		
		assertThrows(RecipeNotFoundException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	Optional<RecipeEntity> optionalRecipe1 = Optional.empty();
            	Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe1);
        		recipeService.getRecipeById(0l);
               
            }
        });
		
	}
	
	@Test
	@DisplayName("Given Invalid RecipeId, when the request is to be update, then throw RecipeNotFoundException")
	public void recipeUpdateTest_Negative() throws RecipeNotFoundException {
		
		assertThrows(RecipeNotFoundException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	
            	Optional<RecipeEntity> optionalRecipe1 = Optional.empty();
            	Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe1);
            	recipeService.recipeUpdate(0l,model);
               
            }
        });
		
	}

	@Test
	@DisplayName("Given Invalid RecipeId, when the request is to be delete, then throw RecipeNotFoundException")
	public void deleteRecipeTest_Negative() throws RecipeNotFoundException {
		
		assertThrows(RecipeNotFoundException.class, new Executable() {
            
            @Override
            public void execute() throws Throwable {
            	
            	Optional<RecipeEntity> optionalRecipe1 = Optional.empty();
            	Mockito.when(recipeRepository.findById(any())).thenReturn(optionalRecipe1);
        		recipeService.deleteRecipe(0l);
               
            }
        });
		
	}
}