package com.trabalho.crud.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

// Adicionar este import para resolver o erro "cannot find symbol: class LocalDateTime"
import java.time.LocalDateTime; // <--- O IMPORT QUE FALTAVA
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trabalho.crud.core.entity.Animal;
import com.trabalho.crud.core.entity.BusinessException;
import com.trabalho.crud.core.repository.AnimalRepository;

@ExtendWith(MockitoExtension.class) 
class AnimalServiceTest {

    @Mock
    private AnimalRepository repository;

    @InjectMocks
    private AnimalService service;

    private Animal animal; 

    @BeforeEach
    void setup() {
        // O construtor Animal.java agora funciona corretamente
        animal = new Animal(1L, "Rex", "Cachorro", "Golden Retriever"); 
    }

    // --- TESTES DE CRIAÇÃO (CREATE) ---
    // --- TESTE PARA UPDATE (0% no seu print) ---
    @Test
    @DisplayName("Deve atualizar um Animal com sucesso")
    void shouldUpdateAnimalSuccess() {
        // ARRANGE: Prepara dados novos para a atualização
        Animal updatedInfo = new Animal(1L, "Rex Atualizado", "Cachorro", "Labrador");
        
        // Simula a busca do animal existente (ID 1L)
        when(repository.findById(1L)).thenReturn(Optional.of(animal));
        // Simula o salvamento do animal atualizado
        when(repository.save(any(Animal.class))).thenReturn(updatedInfo);

        // ACT: Executa o método de update
        Animal result = service.update(1L, updatedInfo);

        // ASSERT: Verifica se o resultado está correto
        assertNotNull(result);
        assertEquals("Rex Atualizado", result.getNome());
        verify(repository, times(1)).findById(1L); // Verifica se a busca foi chamada
        verify(repository, times(1)).save(any(Animal.class)); // Verifica se o save foi chamado
    }

    // --- TESTE PARA AGENDAR CONSULTA (0% no seu print) ---
    @Test
    @DisplayName("Deve agendar uma consulta com sucesso")
    void shouldScheduleAppointmentSuccess() {
        // ARRANGE
        LocalDateTime dataConsulta = LocalDateTime.now().plusDays(1);
        
        // Simula a busca do animal
        when(repository.findById(1L)).thenReturn(Optional.of(animal));
        // Simula o salvamento (pois o método agendarConsulta chama save)
        when(repository.save(animal)).thenReturn(animal);

        // ACT: Executa o método de agendamento
        service.agendarConsulta(1L, dataConsulta, "Dr. Silva");

        // ASSERT
        // Verifica se o método save foi chamado (provando que a lógica foi executada)
        verify(repository, times(1)).save(animal);
        // Verifica se a consulta foi realmente adicionada na entidade
        assertEquals(1, animal.getConsultas().size());
    }

    // --- TESTE PARA LISTAR CONSULTAS (0% no seu print) ---
    @Test
    @DisplayName("Deve listar as consultas de um animal")
    void shouldListAppointmentsSuccess() {
        // ARRANGE
        // Adiciona uma consulta ao mock para termos o que listar
        animal.agendarConsulta(LocalDateTime.now(), "Dr. Teste");
        
        // Simula a busca do animal
        when(repository.findById(1L)).thenReturn(Optional.of(animal));

        // ACT
        List<String> consultas = service.listarConsultas(1L);

        // ASSERT
        assertNotNull(consultas);
        assertEquals(1, consultas.size());
        verify(repository, times(1)).findById(1L);
    }
    @Test
    @DisplayName("Deve salvar um Animal com sucesso")
    void shouldSaveAnimalSuccess() {
        when(repository.save(any(Animal.class))).thenReturn(animal);

        Animal savedAnimal = service.save(new Animal()); 

        assertNotNull(savedAnimal);
        assertEquals("Rex", savedAnimal.getNome());
        verify(repository, times(1)).save(any(Animal.class));
    }

    // --- TESTES DE LEITURA (READ) ---
    @Test
    @DisplayName("Deve listar todos os Animais")
    void shouldFindAllAnimals() {
        Animal animal2 = new Animal(2L, "Miau", "Gato", "Siamês");
        List<Animal> mockList = Arrays.asList(animal, animal2);

        when(repository.findAll()).thenReturn(mockList);

        List<Animal> result = service.findAll(); 

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Miau", result.get(1).getNome());

        verify(repository, times(1)).findAll();
    }
    
    // --- TESTES DE BUSCA POR ID ---
    @Test
    @DisplayName("Deve encontrar Animal por ID quando ele existe")
    void shouldFindAnimalByIdSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(animal));

        Animal result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Rex", result.getNome());
        verify(repository, times(1)).findById(1L);
    }

    // --- TESTES DE EXCLUSÃO (DELETE) ---
    @Test
    @DisplayName("Deve excluir Animal por ID com sucesso")
    void shouldDeleteAnimalByIdSuccess() {
        // Mock: Simula que o Animal FOI ENCONTRADO
        when(repository.findById(1L)).thenReturn(Optional.of(animal));

        // Mock: Simula a deleção
        doNothing().when(repository).deleteById(1L);

        service.deleteById(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L); 
    }
    
    @Test
    @DisplayName("Deve lançar exceção ao tentar excluir Animal inexistente")
    void shouldThrowExceptionOnDeleteNonExistentAnimal() {
        // Mock: Simula que o Animal NÃO FOI ENCONTRADO
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> service.deleteById(99L));
        
        verify(repository, never()).deleteById(anyLong()); 
    }
}