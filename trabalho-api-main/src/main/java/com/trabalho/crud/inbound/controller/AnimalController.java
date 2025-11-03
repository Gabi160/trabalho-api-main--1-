package com.trabalho.crud.inbound.controller;

import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.crud.core.entity.Animal;
import com.trabalho.crud.core.service.AnimalService;

@RestController
@RequestMapping("/Animal")
public class AnimalController {

	private final AnimalService AnimalService;

	public AnimalController(AnimalService AnimalService) {
		this.AnimalService = AnimalService;
	}


	@GetMapping("/{id}")
	public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
		return ResponseEntity.ok(AnimalService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Animal> createAnimal(@RequestBody Animal Animal) {
		return ResponseEntity.status(HttpStatus.CREATED).body(AnimalService.save(Animal));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal Animal) {
		return ResponseEntity.ok(AnimalService.update(id, Animal));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
		AnimalService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
