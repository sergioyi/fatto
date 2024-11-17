# Como usar
Também pode Acessar o projeto funcionando [aqui](https://fatto-three.vercel.app/)

Acesse dentro do diretório [lista_de_tarefas](./lista_de_tarefas) por meio de terminal, e dê o comando para construção da imagem:

```shell
docker build -t "fatto_image" .
```

E construa coloque em execução o banco de dados postgreSQL e um container da imagem fatto_image com o comando:
```shell
docker-compose up
```

E com os containers em execução, execute o [comando sql](query.sql) para criar a tabela; com isso já poderá acessar o projeto [aqui](http://localhost:8080/)

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
### ✅ Lista de Tarefas
É a página principal do sistema.
Deve listar todos os registros mantidos na tabela &quot;Tarefas&quot; (um abaixo do outro).
Todos os campos, exceto &quot;Ordem de apresentação&quot;, devem ser apresentados.
As tarefas devem ser apresentadas ordenadas pelo campo &quot;Ordem de apresentação&quot;.
A tarefa que tiver o &quot;Custo&quot; maior ou igual a R$1.000,00 deverá ser apresentada de forma
diferente (por exemplo: a linha inteira com o fundo amarelo).
Ao lado direito de cada registro devem ser apresentados dois botões (preferencialmente
ícones), uma para executar a função de &quot;Editar&quot; e outro para a função de &quot;Excluir&quot; registro.
Ao final da listagem deve existir um botão para executar a função de &quot;Incluir&quot; registro.

### ✅ Excluir
A função deve excluir o registro da Tarefa escolhida.
É necessário apresentar uma mensagem de confirmação (Sim/Não) para a realização da
exclusão.

### ✅ Editar
A função deve editar o registro da Tarefa escolhida.
Só é possível alterar o &quot;Nome da Tarefa&quot;, o &quot;Custo&quot; e a &quot;Data Limite&quot;.
É necessário verificar se o novo nome da tarefa já existe na base de dados. Se já existir, a
alteração não poderá ser feita.
A implementação pode ser feita de uma das duas formas abaixo (escolha uma):
1) A edição é feita diretamente na tela principal (Lista de Tarefas), onde os três campos
são habilitados para edição.
ou
2) É aberta uma nova tela (popup) para edição dos três campos.

### ✅ Incluir
A função deve permitir a inclusão de uma nova tarefa.
Apenas os campos &quot;Nome da Tarefa&quot;, &quot;Custo&quot; e &quot;Data Limite&quot; são informados pelo usuário.
Os demais campos são gerados automaticamente pelo sistema.
O registro recém-criado será o último na ordem de apresentação.

Não pode haver duas tarefas com o mesmo nome.


### ✅ Reordenação das tarefas
A função deve permitir que o usuário possa alterar a ordem de apresentação de uma tarefa.
A implementação pode ser feita de uma das duas formas abaixo (escolha uma, se possível, as
duas):
1) Com o uso do mouse, o usuário arrasta uma tarefa para cima ou para baixo, soltando
na posição desejada. Estilo drag-and-drop.
ou
2) Em cada linha (registro) deve ter dois botões, uma para &quot;subir&quot; a tarefa na ordem de
apresentação e outro para &quot;descer&quot;. Obviamente a primeira tarefa não poderá &quot;subir&quot;
e nem a última poderá &quot;descer&quot;.