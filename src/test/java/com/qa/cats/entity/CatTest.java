package com.qa.cats.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CatTest {

	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Cat.class).usingGetClass().verify();
	}
	
	@Test
	public void getAndSetTest() {
		Cat cat = new Cat();
	
		cat.setId(1L);
		cat.setAge(4);
		cat.setName("Ford");
		cat.setFavFood("milk");
		cat.setHabitat("wild");
		
		
		assertNotNull(cat.getAge());
		assertNotNull(cat.getId());
		assertNotNull(cat.getName());
		assertNotNull(cat.getFavFood());
		assertNotNull(cat.getHabitat());
		
		assertEquals(cat.getAge(), 4);
		assertEquals(cat.getId(), 1L);
		assertEquals(cat.getName(), "Ford");
		assertEquals(cat.getFavFood(), "milk");
		assertEquals(cat.getHabitat(), "wild");
		
		
	}
	
	@Test
	public void allArgsConstructor() {
		Cat cat = new Cat(1L, 6, "Boris", "Sausages", "Democracy");
		
		assertNotNull(cat.getAge());
		assertNotNull(cat.getId());
		assertNotNull(cat.getName());
		assertNotNull(cat.getFavFood());
		assertNotNull(cat.getHabitat());
		
		
		assertEquals(cat.getAge(), 6);
		assertEquals(cat.getId(), 1L);
		assertEquals(cat.getName(), "Boris");
		assertEquals(cat.getFavFood(), "Sasages");
		assertEquals(cat.getHabitat(), "Democracy");
	}
	
}
