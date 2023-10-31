# Caça Palindromos

Esse micro serviço tem como objetivo buscar Palindromos em matrizes de caracteres quadradas com um limite de linha e
colunas de 10x10.

### Tecnologias

- Java 17
- Spring Boot 3
- Maven
- Swagger

### Extras

- Docker
- Docker-Compose

### Instruções de Build e Execução (Vou disponibilizar 3 formas)

### 1° Opção para executar a aplicação direto pelo .jar

Na pasta raiz onde se encontra o pom.xml executar o comando abaixo para gerar o .jar:

```shell
  mvn package 
```

ou o comando abaixo para buildar sem executar os testes:

```shell
mvn package -DskipTests 
```

Para levantar a aplicação:

```shell
java -jar target/palindrome-0.0.1-beta.jar
```

### 2° Opção para executar a aplicação pelo Dockerfile

Na pasta raiz onde se encontra o pom.xml executar comando abaixo para gerar o .jar:

```shell
  mvn package 
```

ou o comando abaixo para buildar sem executar os testes:

```shell
mvn package -DskipTests 
```

Realizar Build da image com o comando:

```shell
docker build -t <user-do-dockerhub>/palindromos:1.0 .
```

Para levantar a aplicação:

```shell
docker run -p 8080:8080 <user-do-dockerhub>/palindromos:1.0
```

Para levantar a aplicação (Com possibilidade de debug):

```shell
- docker run -p 8080:8080 -p 5005:5005 -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" <user-do-dockerhub>/palindromos:1.0
```

### 3° Opção para executar a aplicação pelo Docker-compose

Criei um repositorio publico para facilitar a execução. Basta executar:

```shell
docker-compose up
```
PS: <user-do-dockerhub> pode ser substituido por qualquer texto.

### Disponibilização da documentação pelo Swagger (Com o projeto rodando)

- http://localhost:8080/api/api-docs
- http://localhost:8080/api/swagger-ui/index.html#/

#### Exemplo payload 10x10
````json
{
  "matrix": [
    ["A", "1", "C", "D", "E", "F", "G", "H", "I", "J"],
    ["K", "L", "0", "N", "O", "P", "Q", "R", "S", "T"],
    ["U", "V", "W", "0", "Y", "Z", "0", "1", "2", "3"],
    ["4", "5", "6", "7", "1", "9", "A", "B", "C", "D"],
    ["E", "F", "G", "O", "S", "S", "O", "L", "M", "N"],
    ["O", "P", "0", "R", "S", "T", "U", "V", "W", "X"],
    ["Y", "0", "0", "A", "R", "A", "R", "A", "6", "7"],
    ["1", "9", "A", "B", "C", "D", "E", "F", "G", "H"],
    ["I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"],
    ["S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1"]
  ]
}
````
#### Resultado 
````json
[
  "ARA",
  "RAR",
  "OSSO",
  "ARARA",
  "1001",
  "ASSA"
]
````


