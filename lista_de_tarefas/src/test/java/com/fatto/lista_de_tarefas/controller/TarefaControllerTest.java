package com.fatto.lista_de_tarefas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fatto.lista_de_tarefas.model.Tarefa;
import com.fatto.lista_de_tarefas.model.TarefaDTO;
import com.fatto.lista_de_tarefas.repository.TarefaRepository;
import com.fatto.lista_de_tarefas.service.TarefaService;

public class TarefaControllerTest {
    
    @Mock
    private TarefaRepository repository;
    @InjectMocks
    private TarefaService service;
    @Mock
    private TarefaService servicemock;
    
    @BeforeEach 
    void setUp() { 
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @DisplayName("incluido com sucesso")
    void testIncluir() {
        ResponseEntity<String> incluir = this.service.Incluir(new TarefaDTO("fazer o teste da fatto", 100d, LocalDate.of(2024, 11, 3)));
        assertEquals(incluir.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("custo 123456789")
    void custo123456789() {
        ResponseEntity<String> incluir = this.service.Incluir(new TarefaDTO("fazer o teste da fatto", 123456789d, LocalDate.of(2024, 11, 3)));
        assertEquals(incluir.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("custo negativo")
    void CustoNegativo() {
        ResponseEntity<String> incluir = this.service.Incluir(new TarefaDTO("fazer o teste da fatto", -123456789d, LocalDate.of(2024, 11, 3)));
        assertEquals(incluir.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(incluir.getBody(), "Não pode haver duas abaixo de 0");
    }

    @Test
    @DisplayName("tudo um")
    void TudoUm() {
        ResponseEntity<String> incluir = this.service.Incluir(new TarefaDTO("1", 1d, LocalDate.of(1111, 11, 1)));
        assertEquals(incluir.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testExcluir() {
        ResponseEntity<String> deletado = ResponseEntity.ok().body("Tarefa excluida");
        Long id = 1l;
        when(this.servicemock.Excluir(1l)).thenReturn(deletado);
        verify(repository).deleteById(id);
    }
    
    @Test 
    @DisplayName("editar com sucesso") 
    public void testEditarComSucesso() { 
        Long id = 1L; 
        TarefaDTO tarefaDTO = new TarefaDTO("nova tarefa", 200d, LocalDate.of(2025, 12, 15)); 
        TarefaDTO tarefaantiga = new TarefaDTO("tarefa antiga", 100d, LocalDate.of(2024, 11, 3));
        Tarefa tarefaExistente = new Tarefa(id, tarefaantiga);
        
         // Configura o mock para simular o comportamento do repositório 
         when(repository.findById(id)).thenReturn(Optional.of(tarefaExistente)); // Chama o método a ser testado 
         ResponseEntity<String> response = service.Editar(id, tarefaDTO); // Verifica se a resposta é 200 OK e contém a mensagem correta 
         assertEquals(HttpStatus.OK, response.getStatusCode()); 
         assertEquals("editado com sucesso !", response.getBody()); // Verifica se o repositório foi chamado para salvar a nova tarefa 
         verify(repository).save(any(Tarefa.class));
        }
    //editar pelo mesmo nome
    //editar por outro que já existe
}
