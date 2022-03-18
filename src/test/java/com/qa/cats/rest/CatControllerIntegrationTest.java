package com.qa.cats.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.cats.entity.Cat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:cat-schema.sql",
		"classpath:cat-data.sql" })
public class CatControllerIntegrationTest {

	@Autowired 
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	

	
	@Test
	public void testCreate() throws Exception {
		
		Cat testCat = new Cat(4, "Crocodile", "chicken", "wild");
		String testCatAsJSON = this.mapper.writeValueAsString(testCat);
		RequestBuilder req = post("/cat/create").content(testCatAsJSON).contentType(MediaType.APPLICATION_JSON);

		Cat testSavedCat = new Cat(2L, 4, "Crocodile", "chicken", "wild");
		String testSavedCatAsJSON = this.mapper.writeValueAsString(testSavedCat);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testSavedCatAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/cat/readById/1");

		ResultMatcher checkStatus = status().isOk();

		Cat savedCat = new Cat(1L, 7, "Nemo", "tuna", "domestic");
		String savedCatAsJSON = this.mapper.writeValueAsString(savedCat);

		ResultMatcher checkBody = content().json(savedCatAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		Cat entry = new Cat(1L, 7, "Nemo", "tuna", "domestic");
		List<Cat> cats = new ArrayList<>();
		cats.add(entry);
		String catsOutputAsJson = this.mapper.writeValueAsString(cats);
		
		this.mvc.perform(get("/cat/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(catsOutputAsJson));
	}
	
	@Test
	public void updateTest() throws Exception{
		Cat entry = new Cat(1L, 4, "Tommy", "tuna", "domestic");
		String entryCatAsJson = this.mapper.writeValueAsString(entry);
		
		Cat result = new Cat(1L, 4, "Tommy", "tuna", "domestic");
		String resultCatAsJson = this.mapper.writeValueAsString(result);
		
		this.mvc.perform(put("/cat/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryCatAsJson))
		.andExpect(status().isAccepted())
		.andExpect(content().json(resultCatAsJson));
	}
	
	
}
