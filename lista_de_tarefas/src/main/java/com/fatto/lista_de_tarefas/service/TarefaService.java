package com.fatto.lista_de_tarefas.service;

import java.util.ArrayList;
import java.util.List;
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
            if (byNometarefa.isPresent()) {
                return ResponseEntity.badRequest().body("Não pode haver duas tarefas com o mesmo nome");
            }
            
            List<Tarefa> all = this.repository.findAll();
            atualizarOrdens(all);

            for (Tarefa tarefa2 : all) {
                if (tarefa2.getOrdem().equals(tarefaDTO.ordem())) {
                    return ResponseEntity.badRequest().body("Não pode haver ordens iguais !");
                }
            }
            this.repository.save(new Tarefa(tarefaDTO));
            return ResponseEntity.ok().body("tarefa incluida");
    }

    public ResponseEntity<String> Excluir(@PathVariable Long id) {
        Optional<Tarefa> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            this.repository.deleteById(id);
            
            List<Tarefa> all = this.repository.findAll();
            atualizarOrdens(all);

            return ResponseEntity.ok().body("Tarefa excluida");
        }
        return null;
    }

    public ResponseEntity<String> Editar(@PathVariable("id") Long id, @RequestBody TarefaDTO tarefaDTO){
        Optional<Tarefa> t = this.repository.findById(id);
        if (!t.isPresent()) { 
            return ResponseEntity.badRequest().body("Tarefa não encontrada"); 
        }
        Tarefa tarefa = t.get();

        List<Tarefa> all = this.repository.findAll();
        all.remove(tarefa);
        for (Tarefa tarefaExistente : all) { 
            if (tarefaExistente.getNometarefa().equals(tarefaDTO.nometarefa())) { 
                return ResponseEntity.badRequest().body("o novo nome da tarefa já existe na base de dados."); 
            } 
        }
        List<Tarefa> all2 = this.repository.findAll();
        all2.remove(t.get());
        for (Tarefa tarefa2 : all2) {
            if (tarefa2.getOrdem().equals(tarefaDTO.ordem())) {
                return ResponseEntity.badRequest().body("Não pode haver ordens iguais !");
            }
        }

        tarefa.setNometarefa(tarefaDTO.nometarefa()); 
        tarefa.setCusto(tarefaDTO.custo()); 
        tarefa.setDatalimite(tarefaDTO.datalimite());
        tarefa.setOrdem(tarefaDTO.ordem());
        this.repository.save(tarefa);

        return ResponseEntity.ok().body("editado com sucesso !");
    }

    public Iterable<Tarefa> ListaTarefas(){
        List<Tarefa> all = this.repository.findAllOrderByOrdem();

        List<Tarefa> lista = new ArrayList<Tarefa>();
        for (Tarefa tarefa : all) {
            Tarefa dto = new Tarefa(tarefa.getId(), tarefa.getNometarefa(), tarefa.getCusto(), tarefa.getDatalimite(), tarefa.getOrdem());
            lista.add(dto);
        }
        return lista;
    }

    public Integer NumeroItems(){
        return this.repository.findAll().size();
    }
    
    //  não funciona direito
    public void Ordena(Long ordem, boolean toup){
        Tarefa tarefa = this.repository.findByOrdem(ordem);
    
        if (toup) {
            Long ordemDecima = ordem - 1;
            Tarefa tarefaDecima = this.repository.findByOrdem(ordemDecima);
            System.out.println("A tarefa acima era: " + tarefaDecima);
            tarefa.setOrdem(ordemDecima); 
            tarefaDecima.setOrdem(ordem);
            this.repository.save(tarefa);
            this.repository.save(tarefaDecima);
        } else {
            Long ordemDebaixo = ordem + 1;
            Tarefa tarefaDebaixo = this.repository.findByOrdem(ordemDebaixo);
            System.out.println("A tarefa abaixo era: " + tarefaDebaixo);
            tarefa.setOrdem(ordemDebaixo); 
            tarefaDebaixo.setOrdem(ordem);
            this.repository.save(tarefa);
            this.repository.save(tarefaDebaixo);
        }
    }
    
    //  desenvolvendo
    private static void atualizarOrdens(List<Tarefa> tarefas) { 
        for (int i = 0; i < tarefas.size(); i++) { 
            tarefas.get(i).setOrdem((long) (i + 1));
        }
    }

}
