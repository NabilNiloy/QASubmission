package com.qa.cats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.cats.entity.Cat;
import com.qa.cats.repo.CatRepo;

@Service
public class CatService implements ServiceMethods<Cat>{

	
	private CatRepo repo;
	
	public CatService(CatRepo repo) {
		this.repo  = repo;
	}
	
	
	@Override
	public Cat create(Cat cat) {
		return this.repo.save(cat);
	}

	@Override
	public List<Cat> readAll() {
		return this.repo.findAll();
	}

	@Override
	public Cat readById(long id) {
		Optional<Cat> getCat = this.repo.findById(id);
		if(getCat.isPresent()) {
			return getCat.get();
		}
		return null;
	}

	@Override
	public Cat update(long id, Cat cat) {
		Optional<Cat> existingCat = this.repo.findById(id);
		if(existingCat.isPresent()) {
			Cat exists = existingCat.get();
			exists.setAge(cat.getAge());
			exists.setName(cat.getName());
			exists.setFavFood(cat.getFavFood());
			exists.setHabitat(cat.getHabitat());
			
			return this.repo.saveAndFlush(exists);
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	

}
