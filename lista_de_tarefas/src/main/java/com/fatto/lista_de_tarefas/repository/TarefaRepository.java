package com.fatto.lista_de_tarefas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatto.lista_de_tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Query("SELECT t FROM Tarefa t WHERE t.nometarefa = :nometarefa")
    Optional<Tarefa> findByNometarefa(@Param("nometarefa") String nometarefa);
    
    @Query("SELECT t FROM Tarefa t ORDER BY t.ordem")
    public List<Tarefa> findAllOrderByOrdem();  
    
    @Query("SELECT t FROM Tarefa t WHERE t.ordem = :ordem")
    public Tarefa findByOrdem(@Param("ordem") Long ordem);
}
