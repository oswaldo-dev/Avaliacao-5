<h1 align="center">API para cadastro de Pedidos e Pagamentos</h1><br>

![como-api-funciona](https://user-images.githubusercontent.com/98189208/179321131-656d79ff-555d-4ca6-a9b5-5ee5f99ae474.jpg)

## `Índice`

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)

## `Descrição do projeto`
<p>Projeto feito para a quinta avalição de java e spring boot. Nela é feita uma API em Java com Spring boot para cadastrar pedidos com itens e suas respectivas ofertas,<br>
buscar o pedido, atualizar e deletar. Também é feita a parte de mensageria com RabbitMQ para enviar o id do pedido, o total, o cpf e o cartao. Essa mensagem é enviada para uma api externa que efetuará o pagamento e devolvera se foi efetivado ou não. Após a o pagamento ser efetivado ou recusado, a api envia uma outra mensagem para a api de pedido que atualiza no banco de dados o seu status e o status do pagamento.</p><br>

## `Funcionalidades e Demonstração da Aplicação`

### `Cadastros:`<br>

Aqui é feito o cadastro dos pedidos, itens e ofertas onde temos os campos.

- `POST -` http://localhost:8080/api/pedido<br>

```
{
    "cpf" : "Campo do tipo String",
    "itens" : [
        {
            "nome" : "Campo do tipo String",
            "dataDeCriacao" : "Campo do tipo String (porém é convertido para LocalDateTime, com o formato dd/mm/yyyy hh:mm:ss)",
            "dataDeValidade" : "Campo do tipo String (porém é convertido para LocalDateTime, com o formato dd/mm/yyyy hh:mm:ss)",
            "valor" : "Campo do tipo BigDecimal",
            "descricao" : "Campo do tipo String",
            "ofertas" : [
                {
                    "nome" : "Campo do tipo String",
                    "dataDeCriacao" : "Campo do tipo String (porém é convertido para LocalDateTime, com o formato dd/mm/yyyy hh:mm:ss)",
                    "dataDeValidade" :"Campo do tipo String (porém é convertido para LocalDateTime, com o formato dd/mm/yyyy hh:mm:ss)",
                    "desconto" : "Campo do tipo BigDecimal",
                    "descricao" : "Campo do tipo String"
                }
            ]
        }
    ],
    "tipoPagamento" : "String so aceitando (só aceita CREDIT_CARD, PIX e BANK_PAYMENT_SLIP)",
    "cartao" : {
            "numeroCartao" : "Campo do tipo String",
            "nomeCartao" : "Campo do tipo String",
            "codigoSeguranca" : "Campo do tipo String aceitando 3 caracters",
                "marca" : "String so aceitando (só aceita MASTERCARD e VISA)",
                "mesExpiracao" : "Campo do tipo Integer com maximo = 12",
                "anoExpiracao" : "Campo do tipo Integer",
                "moeda" : "Campo do tipo String"    
    },
    "total" : "Campo do tipo BigDecimal"
}
```

### `Buscas:`<br>

Aqui fazemos a busca de todos os pedidos já cadastrados.

- `GET -` http://localhost:8080/api/pedido<br>

Aqui fazemos a busca de pedidos por ID.

- `GET -` http://localhost:8080/api/pedido/{id}

Aqui fazemos a busca por cpf.

- `GET -` http://localhost:8080/api/pedido?cpf={cpf}

Aqui fazemos a busca pelo total, ordenando de forma crescente ou decrescente.

- `GET -` http://localhost:8080/api/pedido?sort=total,{asc/desc}


### `Atualizações:`<br>

Aqui fazemos um patch do total de um pedido.

- `PATCH -` http://localhost:8080/api/pedido/{id}/{total}

Aqui fazemos um patch do nome de um item.

- `PATCH -` http://localhost:8080/api/item/{id}/{nome}

### `Exclusões:`<br>

Aqui deletamos um pedido.

`DELETE -` http://localhost:8080/api/pedido/{id}

### `Acesso ao Projeto`

https://github.com/oswaldo-dev/Avaliacao-5
