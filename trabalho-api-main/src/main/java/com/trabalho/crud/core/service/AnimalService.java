package com.trabalho.crud.core.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trabalho.crud.core.entity.Animal;
import com.trabalho.crud.core.entity.BusinessException;
import com.trabalho.crud.core.repository.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    // SEU CÓDIGO ESTÁ CORRETO: retorna Animal e lança exceção se não encontrar.
public Animal findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> BusinessException.notFoundException("Animal não encontrado"));
}

    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    public Animal update(Long id, Animal animal) {
        Animal existingAnimal = this.findById(id);
        animal.setId(existingAnimal.getId());
        return save(animal);
    }

    public void deleteById(Long id) {
        this.findById(id);
        repository.deleteById(id);
    }

    // --- Novo método para agendar consulta ---
    public void agendarConsulta(Long animalId, LocalDateTime dataHora, String veterinario) {
        Animal animal = findById(animalId);

        // Adiciona a consulta na lista do Animal
        animal.agendarConsulta(dataHora, veterinario);

        // Salva o animal (mesmo que a lista de consultas não esteja persistida)
        repository.save(animal);
    }

    // Método opcional para listar consultas de um animal
    public List<String> listarConsultas(Long animalId) {
        Animal animal = findById(animalId);
        return animal.getConsultas();
    }

    public List<Animal> findAll() {
    // Corrigido para retornar a lista de animais do repositório
    return (List<Animal>) repository.findAll();
}
}
