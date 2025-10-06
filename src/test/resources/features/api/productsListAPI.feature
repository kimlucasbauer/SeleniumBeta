# language: pt

Funcionalidade: Endpoint api/productsList

  Cenario: Validar resposta da API Get All Products List
    Dado que a API de produtos esta disponivel
    Quando eu envio uma requisicao GET para a lista de produtos
    Entao o codigo de resposta da api productList deve ser 200
    E o corpo da resposta deve conter a lista de produtos
