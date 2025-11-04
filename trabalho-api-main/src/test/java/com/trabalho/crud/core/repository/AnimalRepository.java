package com.trabalho.crud.core.repository;

import com.trabalho.crud.core.entity.Animal;

//import java.util.List; // Mantemos a importação correta de List

// CORREÇÃO: Mude de CrudRepository para JpaRepository
import org.springframework.data.jpa.repository.JpaRepository; 

// Agora herda de JpaRepository.
// JpaRepository já inclui findAll() que retorna List<Animal>.
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // O método findAll() agora é herdado corretamente do JpaRepository.
    // Você não precisa mais sobrescrevê-lo explicitamente aqui.

    // Se você tiver métodos personalizados, mantenha-os aqui:
    // List<Animal> findByEspecie(String especie);
    // Animal findByNomeIgnoreCase(String nome);
}