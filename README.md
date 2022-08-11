<h1 align="center">API para cadastro de Pedidos e Pagamentos</h1><br>

![como-api-funciona](https://user-images.githubusercontent.com/98189208/179321131-656d79ff-555d-4ca6-a9b5-5ee5f99ae474.jpg)

## `Índice`

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)

## `Descrição do projeto`
<p>Projeto feito para a quinta avalição de java e spring boot. Nela é feita uma API em Java com Spring boot para cadastrar itens, clientes, vincular clientes a cartões e realizar um pedido.<br> 
Também é feita a parte de mensageria com RabbitMQ para enviar o id do pedido, o total, o cpf e o cartao. Essa mensagem é enviada para uma api externa que efetuará o pagamento e devolvera se foi efetivado ou não. Após a o pagamento ser efetivado ou recusado, a api envia uma outra mensagem para a api de pedido que atualiza no banco de dados o seu status e o status do pagamento.</p><br>

## `Funcionalidades e Demonstração da Aplicação`

### `Cadastros:`<br>

Aqui é feito o cadastro do cliente onde temos os campos.

- `POST -` http://localhost:8082/api/clente <br>

```
{
    "cpf" : "Campo do tipo String",
    "nome" : "Campo do tipo String",
    "dataCriacao" : "Campo do tipo LocalDateTime"
}
```

- `POST -` http://localhost:8082/api/item <br>

Aqui é feito o cadastro do item onde temos os campos.

```
{
    "nome" : "Campo do tipo String",
    "dataValidade" : "Campo do tipo LocalDateTime",
    "dataCriacao" : "Campo do tipo LocalDateTime",
    "valor" : "Campo do tipo BigDecimal",
    "descricao" : "Campo do tipo String",
    "estoque" : "Campo do tipo Integer"
}
```

- `POST -` http://localhost:8082/api/cliente/{cpf}/cartoes <br>

Aqui é feito o cadastro dos cartões de um cliente onde temos os campos.

```
{
    "numeroCartao" : "Campo do tipo String",
    "nomeCartao" : "Campo do tipo String",
    "codigoSeguranca" : "Campo do tipo String",
    "mesExpiracao" : "Campo do tipo Integer",
    "anoExpiracao" : "Campo do tipo Integer",
    "marca" : "Campo do tipo String (só aceita MASTERCARD e VISA)"
}
```

- `POST -` http://localhost:8082/api/checkout <br>

Aqui é feito o pedido de itens para determinado cliente onde temos os campos.

```
{
    "itens" : [
        {
            "skuId" : "Campo do tipo String gerado altomaticamente na hora
            de cadastrar um item, ele é informado na hora q cadastramos
            um item ou quando damos um get no item",
            "quantidade" : "Campo do tipo Integer"
        }
    ],
    "cliente" : {
        "clienteId" : "Campo do tipo String",
        "cartaoId" : "Campo do tipo long"
    }
}
```
### `Buscas:`<br>

Aqui fazemos a busca de todos os clientes já cadastrados.

- `GET -` http://localhost:8082/api/cliente <br>

Aqui fazemos a busca de clientes por ID.

- `GET -` http://localhost:8082/api/cliente/{cpf}

Aqui fazemos a busca de todos os itens já cadastrados.

- `GET -` http://localhost:8082/api/item

Aqui fazemos a busca de itens por ID.

- `GET -` http://localhost:8082/api/item/{id}

Aqui fazemos a busca de todos os cartões cadastrados de um cliente.

- `GET -` http://localhost:8082/api/cliente/{cpf}/cartoes

Aqui fazemos a busca de cartões de um cliente por ID.

- `GET -` http://localhost:8082/api/cliente/{cpf}/cartoes/{id}

### `Atualizações:`<br>

Aqui fazemos um put de um cliente.

- `PUT -` http://localhost:8082/api/cliente/{cpf}

Aqui fazemos um put de um item.

- `PUT -` http://localhost:8082/api/item/{id}

Aqui fazemos um put de um cartão de um cliente.

- `PUT -` http://localhost:8082/api/cliente/{cpf}/cartoes/{id}

### `Acesso ao Projeto`

https://github.com/oswaldo-dev/Avaliacao-5
