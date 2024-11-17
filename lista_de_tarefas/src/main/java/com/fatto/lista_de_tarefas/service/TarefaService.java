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

    if(tarefaDTO.custo() < 0){
        return ResponseEntity.badRequest().body("Não pode ter valor negativo");      
    }

    int size = this.repository.findAll().size();
    this.repository.save(new Tarefa(tarefaDTO.nometarefa(), tarefaDTO.custo(), tarefaDTO.datalimite(), size+1l));
    return ResponseEntity.ok().body("Tarefa incluída");
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

        tarefa.setNometarefa(tarefaDTO.nometarefa()); 
        tarefa.setCusto(tarefaDTO.custo()); 
        tarefa.setDatalimite(tarefaDTO.datalimite());
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
    
    public ResponseEntity<String> Ordena(Long ordem, boolean toup) {
        Tarefa tarefa = this.repository.findByOrdem(ordem);
    
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa com a ordem " + ordem + " nao encontrada");
        }
    
        if (toup) {
            if (ordem == 1) {
                 return ResponseEntity.badRequest().body("Nao e possível mover a tarefa com ordem 1 para cima.");
            }
    
            Long ordemDecima = ordem - 1;
            Tarefa tarefaDecima = this.repository.findByOrdem(ordemDecima);
    
            if (tarefaDecima == null) {
                 return ResponseEntity.badRequest().body("Tarefa com a ordem " + ordemDecima + " nao encontrada");
            }
    
            System.out.println("A tarefa acima era: " + tarefaDecima);
            tarefa.setOrdem(null);
            this.repository.save(tarefa);
    
            tarefaDecima.setOrdem(ordem);
            this.repository.save(tarefaDecima);
    
            tarefa.setOrdem(ordemDecima);
            this.repository.save(tarefa);
        } else {
            List<Tarefa> todasTarefas = this.repository.findAll();
            Long ultimaOrdem = todasTarefas.stream().mapToLong(Tarefa::getOrdem).max().orElseThrow(() -> 
                new IllegalArgumentException("Não foi possível determinar a ultima ordem da lista."));
    
            if (ordem.equals(ultimaOrdem)) {
                 return ResponseEntity.badRequest().body("Não é possível mover a ultima tarefa para baixo.");
            }
    
            Long ordemDebaixo = ordem + 1;
            Tarefa tarefaDebaixo = this.repository.findByOrdem(ordemDebaixo);
    
            if (tarefaDebaixo == null) {
                 return ResponseEntity.badRequest().body("Tarefa com a ordem " + ordemDebaixo + " nao encontrada");
            }
    
            System.out.println("A tarefa abaixo era: " + tarefaDebaixo);
            tarefa.setOrdem(null);
            this.repository.save(tarefa);
    
            tarefaDebaixo.setOrdem(ordem);
            this.repository.save(tarefaDebaixo);
    
            tarefa.setOrdem(ordemDebaixo);
            this.repository.save(tarefa);
            return ResponseEntity.ok().build();
        }
                return null;
    }
    
    
    private void atualizarOrdens(List<Tarefa> tarefas) { 
        for (int i = 0; i < tarefas.size(); i++) { 
            Tarefa tarefa = tarefas.get(i);
            tarefa.setOrdem((long) (i + 1));
            this.repository.save(tarefa);
        }
    }
    
}
