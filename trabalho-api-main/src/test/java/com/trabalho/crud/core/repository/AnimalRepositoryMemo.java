package com.trabalho.crud.core.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;

import com.trabalho.crud.core.entity.Animal;

@ActiveProfiles("test")
@Repository
public class AnimalRepositoryMemo implements AnimalRepository {

	private final List<Animal> Animals = new ArrayList<Animal>();

	@Override
	public List<Animal> findAll() {
		return Animals;
	}

	@Override
	public Optional<Animal> findById(Long id) {
		return Animals.stream().filter(Animal -> Animal.getId().equals(id)).findFirst();
	}

	@Override
	public Animal save(Animal Animal) {
		var id = Animals.size() + 1;
		Animal.setId((long) id);
		Animals.add(Animal);
		return Animal;
	}

	@Override
	public void deleteById(Long id) {
		Animals.removeIf(Animal -> Animal.getId() == id);
	}

}
