# Documentação API Eventos

Esta documentação descreve os endpoints da API para gerenciamento de eventos.

## Recursos

* **GET /eventos**

* **GET /eventos/{id}**

* **POST /eventos**

* **PUT /eventos/{id}**

* **DELETE /eventos/{id}**

* **GET /eventos/busca**

* **GET /eventos/contagem**

## Listar Eventos
Endpoint para listar todos os eventos cadastrados.

**Requisição**

*GET / eventos*

**Resposta**

*Status: 200 OK*

``` [
  {
    "id": 1,
    "nome": "Evento 1",
    "data": "2023-05-01",
    "categoria": "TEATRO",
    "status": "DISPONIVEL"
  },
  {
    "id": 2,
    "nome": "Evento 2",
    "data": "2023-06-01",
    "categoria": "SHOW",
    "status": "CANCELADO"
  },
  ...
]
```
## Buscar Evento por ID
Endpoint para buscar um evento pelo ID.

**Requisição**

*GET /eventos/{id}*

| Parâmetro | 	Tipo  | Descrição             |
|-----------|--------|-----------------------|
| Id        | number | ID do evento a buscar |

*Status: 200 OK*

```
{
  "id": 1,
  "nome": "Evento 1",
  "data": "2023-05-01",
  "categoria": "TEATRO",
  "status": "DISPONIVEL"
}
```
*Status: 404 Not Found*

## Criar Evento

Endpoint para criar um novo evento.

**Requisição**

*POST /eventos*

| Parâmetro | 	Tipo  | Descrição                                            |
|-----------|--------|------------------------------------------------------|
| nome      | string | Nome do evento (obrigatório)                         |
| data      | string | Data do evento  (obrigatório)                        |
| categoria | string | Categoria do evento (obrigatório)                    |
| status    | string | Status do evento (opcional, valor padrão DISPONIVEL) |		

**Resposta**

*Status: 201 Created*
```
{
"id": 1,
"nome": "Evento 1",
"data": "2023-05-01",
"categoria": "TEATRO",
"status": "DISPONIVEL"
}
```
## Atualizar Evento

Endpoint para atualizar um evento existente.

**Requisição**

*PUT /eventos/{id}*	

| Parâmetro | 	Tipo  | Descrição                                            |
|-----------|--------|------------------------------------------------------|
| id        | number | ID do evento a ser atualizado (obrigatório)          |
| nome      | string | Nome do evento (obrigatório)                         |
| data      | string | Data do evento  (obrigatório)                        |
| categoria | string | Categoria do evento (obrigatório)                    |
| status    | string | Status do evento (opcional, valor padrão DISPONIVEL) |		

## Deletar Evento

Endpoint para deletar um evento existente.

*Status 204 ou 404*

 #### Exemplo de Uso:

**DELETE** http://localhost:8080/eventos/{id} 

## BuscarEventosPorDataECategoria

Endpoint buscar eventos de acordo com os parâmetros informados

*Status 404*
#### Exemplo de Uso:

**GET** http://localhost:8080/eventos/busca?data=2023-05-01&categoria=TEATRO

**GET** http://localhost:8080/eventos/busca?data=2023-05-01

**GET** http://localhost:8080/eventos/busca?categoria=TEATRO

**GET** http://localhost:8080/eventos/busca?categoria=FESTA&status=DISPONIVEL

**GET** http://localhost:8080/eventos/busca?status=CANCELADO

## ContarEventosPorCategoria

Endpoint contar quantos eventos existem em cada categoria


*Status 404*
#### Exemplo de Uso:

**GET** http://localhost:8080/eventos/contagem

**GET** http://localhost:8080/eventos/contagem?status=DISPONIVEL

## Tratar

trata as exceções do tipo ```ConstraintViolationException``` que podem ser lançadas pelos métodos de validação.

#### Exemplo de Uso:

*Esse método não é chamado diretamente, ele é chamado automaticamente quando ocorre uma exceção*

# ParticipanteController

## Endpoints
**POST /participantes**

Cria um novo participante.

#### Exemplo de requisição:

```
{
"nome": "Fulano de Tal",
"email": "fulano@exemplo.com"
}
```
**Exemplo de resposta**

```
{
  "id": 1,
  "nome": "Fulano de Tal",
  "email": "fulano@exemplo.com"
}
```

## POST /participantes/{eventoId}/adicionarEvento/{participanteId}
Adiciona um participante a um evento.

**Exemplo de requisição:**

*POST /participantes/1/adicionarEvento/2*
```
{
  "id": 1,
  "nome": "Evento 1",
  "data": "2023-03-22",
  "participantes": [
    {
      "id": 2,
      "nome": "Fulano de Tal",
      "email": "fulano@exemplo.com"
    }
  ]
}
```
## PUT /participantes/{id}
Atualiza as informações de um participante.

**Exemplo de requisição:**

````
{
  "nome": "Fulano da Silva",
  "email": "fulano.silva@exemplo.com"
}
````
**Exemplo de resposta:**

````{
  "id": 2,
  "nome": "Fulano da Silva",
  "email": "fulano.silva@exemplo.com"
}
````

## DELETE /participantes/{id}
Exclui um participante.

**Exemplo de requisição:**

*DELETE /participantes/2*

Exemplo de resposta:

````
 [
{
"id": 1,
"nome": "Fulano de Tal",
"email": "fulano@exemplo.com"
}
]
````
