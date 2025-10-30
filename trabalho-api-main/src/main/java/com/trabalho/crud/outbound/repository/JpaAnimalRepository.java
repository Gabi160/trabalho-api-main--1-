package com.trabalho.crud.outbound.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trabalho.crud.core.entity.Animal;
import com.trabalho.crud.core.repository.AnimalRepository;

@Profile("!test")
public interface JpaAnimalRepository extends AnimalRepository, JpaRepository<Animal, Long> {

}
