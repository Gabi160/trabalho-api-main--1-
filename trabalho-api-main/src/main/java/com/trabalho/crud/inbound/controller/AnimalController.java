package com.trabalho.crud.inbound.controller;

import com.trabalho.crud.core.entity.Animal;
import com.trabalho.crud.core.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/animais")
// Configuração CORS: Permite que o frontend React acesse este controlador
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // --- Endpoints CRUD Básicos ---

    @GetMapping
    public ResponseEntity<Object> listarTodos() {
        // Supondo que você adicionará um método findAll() no Service
        // Por enquanto, vamos retornar uma lista vazia ou implementar o findAll()
        // Adicione este método ao seu AnimalService: public List<Animal> findAll() { return repository.findAll(); }
        return ResponseEntity.ok(animalService.findAll()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        Animal animal = animalService.findById(id);
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Animal> criarAnimal(@RequestBody Animal animal) {
        Animal novoAnimal = animalService.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizarAnimal(@PathVariable Long id, @RequestBody Animal animalDetalhes) {
        Animal animalAtualizado = animalService.update(id, animalDetalhes);
        return ResponseEntity.ok(animalAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        animalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    // --- Endpoint para Lógica de Negócio (Consultas) ---
    
    // Este endpoint recebe os dados da consulta no corpo da requisição (Map)
    @PostMapping("/{animalId}/agendar-consulta")
    public ResponseEntity<Void> agendarConsulta(@PathVariable Long animalId, @RequestBody Map<String, String> dadosConsulta) {
        
        // A data/hora deve ser enviada como String no formato ISO 8601 (ex: "2025-11-04T10:30:00")
        LocalDateTime dataHora = LocalDateTime.parse(dadosConsulta.get("dataHora"));
        String veterinario = dadosConsulta.get("veterinario");
        
        animalService.agendarConsulta(animalId, dataHora, veterinario);
        
        return ResponseEntity.ok().build();
    }
    
    // Endpoint para listar as consultas (lista transitória)
    @GetMapping("/{animalId}/consultas")
    public ResponseEntity<List<String>> listarConsultas(@PathVariable Long animalId) {
        List<String> consultas = animalService.listarConsultas(animalId);
        return ResponseEntity.ok(consultas);
    }
}