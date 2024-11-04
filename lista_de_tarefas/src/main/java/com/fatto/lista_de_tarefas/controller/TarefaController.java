package com.fatto.lista_de_tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatto.lista_de_tarefas.model.TarefaDTO;
import com.fatto.lista_de_tarefas.service.TarefaService;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping("/")
    public ResponseEntity<String> Incluir(@RequestBody TarefaDTO tarefaDTO) {
        return service.Incluir(tarefaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Excluir(@PathVariable("id") Long id){
        return service.Excluir(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> Editar(@PathVariable("id") Long id, @RequestBody TarefaDTO tarefaDTO){
        return this.service.Editar(id, tarefaDTO);
    }
}
