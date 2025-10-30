package com.trabalho.crud.core.repository;

import java.util.List;
import java.util.Optional;

import com.trabalho.crud.core.entity.Animal;

public interface AnimalRepository {

	List<Animal> findAll();

	Optional<Animal> findById(Long id);

	Animal save(Animal Animal);

	void deleteById(Long id);

}
