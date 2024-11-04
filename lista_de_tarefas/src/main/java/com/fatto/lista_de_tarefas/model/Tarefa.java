package com.fatto.lista_de_tarefas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tarefas")
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nometarefa;
    private float custo;
    private LocalDate datalimite;
    @Column(unique = true)
    private Integer ordem;
    public Tarefa() { }
    public Tarefa(TarefaDTO tarefa){
        this.nometarefa = tarefa.nometarefa();
        this.custo = tarefa.custo();
        this.datalimite = tarefa.datalimite();
        this.ordem = tarefa.ordem();
    }
    public Tarefa(Long id, TarefaDTO tarefa){
        this.id = id;
        this.nometarefa = tarefa.nometarefa();
        this.custo = tarefa.custo();
        this.datalimite = tarefa.datalimite();
        this.ordem = tarefa.ordem();
    }
}
