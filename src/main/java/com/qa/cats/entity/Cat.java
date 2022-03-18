package com.qa.cats.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Cat {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column
	@Min(1)
	@Max(18)
	private int age;
	
	@Column(unique =  true, nullable = false)
	private String name;
	
	@Column
	private String favFood;
	
	@Column
	private String habitat;
	
	//Default Constructor
	public Cat() {}

	
	//to create ducks
	public Cat(int age, String name, String favFood, String habitat) {
		super();
		this.age = age;
		this.name = name;
		this.favFood = favFood;
		this.habitat = habitat;
	}

	//to test
	public Cat(long id, int age, String name, String favFood, String habitat) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.favFood = favFood;
		this.habitat = habitat;
	}

	//Getters and Setters
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFavFood() {
		return favFood;
	}


	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}


	public String getHabitat() {
		return habitat;
	}


	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}


	@Override
	public String toString() {
		return "Cat [id=" + id + ", age=" + age + ", name=" + name + ", favFood=" + favFood + ", habitat=" + habitat
				+ "]";
	}


	//more testing
	@Override
	public int hashCode() {
		return Objects.hash(age, favFood, habitat, id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		return age == other.age && Objects.equals(favFood, other.favFood) && Objects.equals(habitat, other.habitat)
				&& id == other.id && Objects.equals(name, other.name);
	}



	
	
	

	
}
