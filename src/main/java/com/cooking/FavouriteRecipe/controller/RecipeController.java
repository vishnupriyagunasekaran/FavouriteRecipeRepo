package com.cooking.FavouriteRecipe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooking.FavouriteRecipe.exception.RecipeNotFoundException;
import com.cooking.FavouriteRecipe.model.Recipe;
import com.cooking.FavouriteRecipe.service.RecipeService;

@RestController
@RequestMapping("/rest/favouriteRecipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecipes()
			throws RecipeNotFoundException {
		return new ResponseEntity<>(recipeService.getAllRecipes(),HttpStatus.OK);
	}

	@GetMapping(value = "/recipeId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecipeById(@PathVariable(value = "id") Long recipeId)
			throws RecipeNotFoundException {
		return new ResponseEntity<>(recipeService.getRecipeById(recipeId),HttpStatus.OK);
	}
	
	@PostMapping(value = "/recipe" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recipeAdd(@Valid @RequestBody Recipe recipeModel) {
		return new ResponseEntity<>(recipeService.recipeAdd(recipeModel),HttpStatus.CREATED);
		
	}	
	
	@PutMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recipeUpdate(@PathVariable("id") Long id,@Valid @RequestBody Recipe recipe) throws RecipeNotFoundException {
		return new ResponseEntity<>(recipeService.recipeUpdate(id, recipe),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) throws RecipeNotFoundException {
		
		return new ResponseEntity<>(recipeService.deleteRecipe(id),HttpStatus.OK);
	}
	

}
