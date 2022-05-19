package com.cooking.FavouriteRecipe.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cooking.FavouriteRecipe.model.JwtRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class JwtControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	JwtRequestModel requestModel;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setup(){
		
		requestModel = new JwtRequestModel();
		requestModel.setUsername("admin");
		requestModel.setPassword("admin");
    	
	}
	
	@Test
	@Description("Get the token and the status should be 200")
	public void givenRequest_ForToken_thenReturn200() throws Exception{

		ResultActions response = mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestModel)));

		response.andExpect(status().isOk())
		.andDo(print());
	}
}
