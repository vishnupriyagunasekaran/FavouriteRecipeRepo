package com.cooking.FavouriteRecipe.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cooking.FavouriteRecipe.model.Recipe;
import com.cooking.FavouriteRecipe.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RecipeControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	private RecipeService recipeService;
	
	private static String authJWTToken = "";
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Value("${token.validity}")
    private long tokenValidity;

	@Value("${secret}")
	private String mySecretKey;

	@BeforeEach
    public void tokenGenerator() {


        Map<String, Object> claims = new HashMap<>();
        authJWTToken = Jwts.builder().setClaims(claims).setSubject("admin").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS512, mySecretKey).compact();


    }
	
	@Test
	@Description("Get All the Recipes and the status should be 200")
	public void givenRequest_ForRecipes_thenReturn200() throws Exception{

		ResultActions response = mockMvc.perform(get("/rest/favouriteRecipe/recipes")
				.header("Authorization", "Bearer " + authJWTToken)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		response.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	@Description("Given RecipeId, when getting the recipe details, then recipe details should return with 200")
    public void givenRecipeId_whenGetRecipe_thenReturn200() throws Exception{
        
        ResultActions response = mockMvc.perform(get("/rest/favouriteRecipe/recipeId/{id}", 1l)
        		.header("Authorization", "Bearer " + authJWTToken)
        		.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

        
        response.andExpect(status().isOk())
                .andDo(print());
    }
	
	@Test
	@Description("Given RecipeDetails, when adding the recipe details, then details should add with 201")
    public void givenRecipeId_whenAddRecipe_thenReturn401() throws Exception{
        
		List<String> ingredientsList = new ArrayList<>();
		ingredientsList.add("salt and 1 pound (450 g) of pasta");
		Recipe model = new Recipe(2l,"pasta", true, "19‐05‐2022 09:39", "Season the beaten eggs well with salt and pepper",
                ingredientsList, 2);
		ResultActions response = mockMvc.perform(post("/rest/favouriteRecipe/recipe")
				.header("Authorization", "Bearer " + authJWTToken)
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(model)));

        response.andExpect(status().isCreated())
                .andDo(print());
    }
	
	@Test
	@Description("Given RecipeDetails, when adding the recipe details, then details should add with 201")
    public void givenRecipeId_whenUpdateRecipe_thenReturn401() throws Exception{
        
		List<String> ingredientsList = new ArrayList<>();
		ingredientsList.add("salt and 1 pound (450 g) of pasta");
		Recipe model = new Recipe(2l, "pasta", true, "19‐05‐2022 09:39", "Season the beaten eggs well with salt and pepper",
                ingredientsList, 2);
		
		ResultActions response = mockMvc.perform(put("/rest/favouriteRecipe/recipe/{id}",1l)
				.header("Authorization", "Bearer " + authJWTToken)
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(model)));

        response.andExpect(status().isOk())
                .andDo(print());
    }
	
	
	@Test
	@Description("Given RecipeId, when deleting the recipe details, then details should delete with 200")
    public void givenRecipeId_whenDeleteRecipe_thenReturn200() throws Exception{
        
		ResultActions response = mockMvc.perform(delete("/rest/favouriteRecipe/recipe/"+1)
				.header("Authorization", "Bearer " + authJWTToken)
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andDo(print());
    }

}
