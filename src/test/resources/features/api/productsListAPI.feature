# language: pt

Funcionalidade: Endpoint api/productsList

  Cenario: Validar resposta da API Get All Products List
    Dado que a API de produtos esta disponivel
    Quando eu envio uma requisicao GET para a lista de produtos
    Entao o status code da resposta da api productList deve ser 200
    E o response code de resposta da api productList deve ser 200
    E valido o response header da api de lista de produtos
    E o corpo da resposta deve conter a lista de produtos

  Cenario: Validar bloqueio da API para tipo de requisicao incorreta DELETE All Products List
    Dado que a API de produtos esta disponivel
    Quando eu envio uma requisicao DELETE para a lista de produtos
    Entao o status code da resposta da api productList deve ser 200
    E o response code de resposta da api productList deve ser 405