package com.qa.cats.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.cats.controller.CatController;
import com.qa.cats.entity.Cat;
import com.qa.cats.service.CatService;

@SpringBootTest
public class CatControllerUnitTest {
	@Autowired
	private CatController controller;
	
	@MockBean
	private CatService service;
	
	@Test
	public void createCatTest() {
		Cat cat = new Cat(11, "Tibet", "playdoh", "domestic");
		
		Mockito.when(this.service.create(cat)).thenReturn(cat);
		
		ResponseEntity<Cat> response = new ResponseEntity<Cat>(cat, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(this.controller.createCat(cat));
		
		Mockito.verify(this.service, times(1)).create(cat);
	}
	
	@Test
	public void UpdateCatTest() {
		Cat cat = new Cat(11, "Tibet", "playdoh", "domestic");
		
		Mockito.when(this.service.update(1, cat)).thenReturn(cat);
		
		ResponseEntity<Cat> response = new ResponseEntity<Cat>(cat, HttpStatus.ACCEPTED);
		
		assertThat(response).isEqualTo(this.controller.updateCat(1, cat));
		
		Mockito.verify(this.service, times(1)).update(1, cat);
	}
	
	@Test
	public void ReadByIdTest() {
		Cat cat = new Cat(11, "Tibet", "playdoh", "domestic");
		
		Mockito.when(this.service.readById(1)).thenReturn(cat);
		
		ResponseEntity<Cat> response = new ResponseEntity<Cat>(cat, HttpStatus.OK);
		
		assertThat(response).isEqualTo(this.controller.readById(1));
		
		Mockito.verify(this.service, times(1)).readById(1);
	}
	
	@Test
	public void deleteCatTest() {
		Cat cat = new Cat(11, "Tibet", "playdoh", "domestic");
		
		Mockito.when(this.service.delete(1)).thenReturn(true);
		
		ResponseEntity<Cat> response = new ResponseEntity<Cat>( HttpStatus.NO_CONTENT);
		
		assertThat(response).isEqualTo(this.controller.deleteCat(1));
		
		Mockito.verify(this.service, times(1)).delete(1);
	
	}
	
}
