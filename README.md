# Sistema Lista de Tarefas
## Desenvolva um sistema web para cadastro de Tarefas e publique a aplicação disponibilizando o link para acesso. Os dados (tarefas) devem ser mantidos em um banco de dados.

```
✅ Tabela: Tarefas
Campos:
-  Identificador da tarefa (chave primária)
-  Nome da tarefa
-  Custo (R$)
-  Data limite
-  Ordem de apresentação (campo numérico, não repetido, que servirá para ordenar os
registros na tela)
```

### ❌ Editar
A função deve editar o registro da Tarefa escolhida.
Só é possível alterar o &quot;Nome da Tarefa&quot;, o &quot;Custo&quot; e a &quot;Data Limite&quot;.
É necessário verificar se o novo nome da tarefa já existe na base de dados. Se já existir, a
alteração não poderá ser feita.
A implementação pode ser feita de uma das duas formas abaixo (escolha uma):
1) A edição é feita diretamente na tela principal (Lista de Tarefas), onde os três campos
são habilitados para edição.
ou
2) É aberta uma nova tela (popup) para edição dos três campos.

### ❌ Excluir
A função deve excluir o registro da Tarefa escolhida.
É necessário apresentar uma mensagem de confirmação (Sim/Não) para a realização da
exclusão.

### ✅ Incluir
A função deve permitir a inclusão de uma nova tarefa.
Apenas os campos &quot;Nome da Tarefa&quot;, &quot;Custo&quot; e &quot;Data Limite&quot; são informados pelo usuário.
Os demais campos são gerados automaticamente pelo sistema.
O registro recém-criado será o último na ordem de apresentação.

Não pode haver duas tarefas com o mesmo nome.
