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
import com.cooking.FavouriteRecipe.model.Recipe;
import com.cooking.FavouriteRecipe.service.RecipeService;

/** 
 * The RecipeController handles the data to 
 * get, save, update and delete the recipe
 */
@RestController
@RequestMapping("/rest/favouriteRecipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	/**
	 * This method is used to get all the recipes 
	 * @return ResponseEntity This returns the recipes
	 */
	@GetMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecipes() {
		return new ResponseEntity<>(recipeService.getAllRecipes(),HttpStatus.OK);
	}

	/**
	 * This method is used to get the recipe for particular Id
	 * @param recipeId This is the parameter to get Id
	 * @return ResponseEntity This returns the recipe with success message
	 */
	@GetMapping(value = "/recipeId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRecipeById(@PathVariable(value = "id") Long recipeId) {
		return new ResponseEntity<>(recipeService.getRecipeById(recipeId),HttpStatus.OK);
	}
	
	/**
	 * This method is used to add the recipe
	 * @param recipe This is the parameter to save recipe
	 * @return ResponseEntity This returns the added recipe
	 */
	@PostMapping(value = "/recipe" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recipeAdd(@Valid @RequestBody Recipe recipeModel) {
		return new ResponseEntity<>(recipeService.recipeAdd(recipeModel),HttpStatus.CREATED);
		
	}	
	
	
	/**
	 * @param id This is the first parameter to upadte the recipe
	 * @param recipe This is the second parameter to upate the recipe
	 * @return ResponseEntity This returns the updated Recipe
	 */
	@PutMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> recipeUpdate(@PathVariable("id") Long id,@Valid @RequestBody Recipe recipe) {
		return new ResponseEntity<>(recipeService.recipeUpdate(id, recipe),HttpStatus.OK);
	}
	
	/**
	 * This method is used to delete the recipe for particular Id
	 * @param recipeId This is the parameter to delete Id
	 * @return ResponseEntity This returns the success message
	 */
	@DeleteMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteRecipe(@PathVariable("id") Long id) {
		return new ResponseEntity<>(recipeService.deleteRecipe(id),HttpStatus.OK);
	}
	

}
