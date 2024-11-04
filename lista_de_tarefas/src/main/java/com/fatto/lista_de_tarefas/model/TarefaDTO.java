package com.fatto.lista_de_tarefas.model;

import java.time.LocalDate;

public record TarefaDTO(String nometarefa, float custo, LocalDate datalimite, Integer ordem) {}
