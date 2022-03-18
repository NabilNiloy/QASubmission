package com.qa.cats.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.cats.entity.Cat;
import com.qa.cats.repo.CatRepo;

@SpringBootTest
public class CatServiceUnitTest {
	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	public void createCatTest() {
		Cat input = new Cat(6, "Boris", "Sausages", "Democracy");
		Cat output = new Cat(1L, 6, "Boris", "Sausages", "Democracy");

		Mockito.when(this.repo.save(input)).thenReturn(output);
	
		assertEquals(output, this.service.create(input));

		Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	@Test
	public void readByIdTest() {
	
		Optional<Cat> optionalOutput = Optional.of(new Cat(1L, 6, "Boris", "Sausages", "Democracy"));
		Cat output = new Cat(1L, 6, "Boris", "Sausages", "Democracy");
		
	
		Mockito.when(this.repo.findById(Mockito.anyLong())).thenReturn(optionalOutput);
		
		assertEquals(output, this.service.readById(Mockito.anyLong()));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(Mockito.anyLong());
	}
	
	
	@Test
	public void readAllTest() {
		Cat output = new Cat(1L, 6, "Boris", "Sausages", "Democracy");
		List<Cat> times = new ArrayList<Cat>();
		times.add(output);
		
		Mockito.when(this.repo.findAll()).thenReturn(times);
		
		assertEquals(times, this.service.readAll());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	
	@Test
	public void deleteTrueTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);
		
		assertTrue(this.service.delete(1L));
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	public void UpdateCatTest() {
		Optional<Cat> optionalOutput = Optional.of(new Cat(1L, 6, "Boris", "Sausages", "Democracy"));
		Cat input = new Cat(1, 6, "Sheldon", "science", "lab");
		Cat output = new Cat(1L, 6, "Sheldon", "science", "lab");

		Mockito.when(this.repo.findById(1L)).thenReturn(optionalOutput);
		
		
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);
		assertEquals(output, this.service.update(1L, input));
	
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
	}
	
	
	
}
