package com.fatto.lista_de_tarefas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fatto.lista_de_tarefas.model.Tarefa;
import com.fatto.lista_de_tarefas.model.TarefaDTO;
import com.fatto.lista_de_tarefas.repository.TarefaRepository;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    public ResponseEntity<String> Incluir(@RequestBody TarefaDTO tarefaDTO) {
            Optional<Tarefa> byNometarefa = this.repository.findByNometarefa(tarefaDTO.nometarefa());
            if (!byNometarefa.isPresent()) {
                this.repository.save(new Tarefa(tarefaDTO));
                return ResponseEntity.ok().body("tarefa incluida");
            }
            return ResponseEntity.badRequest().body("Não pode haver duas tarefas com o mesmo nome");
    }

    public ResponseEntity<String> Excluir(@PathVariable Long id) {
        Optional<Tarefa> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().body("Tarefa excluida");
        }
        return null;
    }

    public ResponseEntity<String> Editar(@PathVariable("id") Long id, @RequestBody TarefaDTO tarefaDTO){
        Optional<Tarefa> t = this.repository.findById(id);
        Tarefa tarefa = t.get();
        if (!tarefaDTO.nometarefa().equals(tarefa.getNometarefa()) && tarefaDTO.custo() != tarefa.getCusto() && !tarefaDTO.datalimite().equals(tarefa.getDatalimite())) {
            this.repository.save(new Tarefa(id, new TarefaDTO(tarefaDTO.nometarefa(), tarefaDTO.custo(), tarefaDTO.datalimite(), tarefaDTO.ordem())));
            return ResponseEntity.ok().body("editado com sucesso");
        }
        return ResponseEntity.badRequest().body("os campos devem ser diferentes dos valores atuais");
    }
}
