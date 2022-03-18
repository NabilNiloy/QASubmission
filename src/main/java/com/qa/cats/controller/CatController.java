package com.qa.cats.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.cats.entity.Cat;
import com.qa.cats.service.CatService;

@RestController
@RequestMapping("/cat")
public class CatController {

	private CatService service;
	
	@Autowired
	public CatController(CatService service) {
		this.service = service;
	}

	
	@PostMapping("/create")
	public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
		return new ResponseEntity<Cat>(this.service.create(cat), HttpStatus.CREATED);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<Cat>> readAllCats() {
		return new ResponseEntity<List<Cat>>(this.service.readAll(), HttpStatus.OK);
	}

	@GetMapping("/readById/{id}")
	public ResponseEntity<Cat> readById(@PathVariable long id) {
		return new ResponseEntity<Cat>(this.service.readById(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Cat> updateCat(@PathVariable long id, @RequestBody Cat cat) {
		return new ResponseEntity<Cat>(this.service.update(id, cat), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCat(@PathVariable long id) {
		return (this.service.delete(id)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
