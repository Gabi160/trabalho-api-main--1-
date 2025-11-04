package com.trabalho.crud.core.repository;

import com.trabalho.crud.core.entity.Animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Animal.
 * Estende JpaRepository para herdar operações CRUD e paginação.
 * <Animal, Long> -> (Entidade, Tipo do ID da Entidade)
 */
@Repository
// Note o tipo genérico correto: <Entidade, TipoID>
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // O Spring Data JPA automaticamente implementa métodos baseados no nome,
    // se você precisar de consultas específicas que não sejam as operações CRUD básicas.
    
    // Exemplo: Encontrar todos os animais por espécie
    List<Animal> findByEspecie(String especie);
    
    // Exemplo: Encontrar um animal pelo nome, ignorando o case
    Animal findByNomeIgnoreCase(String nome);
}