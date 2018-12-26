# Framework CRUD JAVA DAO

- Adicionar o **HelperDAO.jar** ao projeto
- Criar um extends para HelperDAO<entity>
- Implementar os métodos abstratos

#### CRUD Interface
 - save(T entity)
  	- Adicionar a query "insert into" para tabela (T)

 - update(T entity)
	- Adicionar a query "update" para a tabela (T)
	- execute(query);
  
 - Optional<entity> getById
	- Realiza uma busca por identtificador da tabela

 - List<Optional<entity>> getAll()
	- Retorna uma lista com todos os itens da tabela
	
#### HelperDAO
###### Métodos abstrados
 - openConnection() -> Abre a conexão com banco de dados
 - putEntity(ResultSet rs) -> Preencher a entidade com os valores do ResultSet

###### Métodos 
 - setConnection(*connection*) -> Definir a conexão com banco de dados
 - execute(*query*) -> executa INSERT ou UPADTE na tabela
 - executeQuery(*query*) -> executa o "SELECT" e retorna uma lista
 	- getLista() -> retorna o resultado do executeQuery
 - executeUnique(*query*) -> executa o "SELECT" e retorna uma única linha da tabela
 	- getUnique() -> retorna o resultado do executeUnique
 	
#### Validation
 - hasError(Exception ou String) -> método para quarda a mensagem de erro do processo
 - isValid -> define se a execução é válida
 - message -> exibe a mensagem de erro;