# language: pt

Funcionalidade: Validar api com cenarios imperativos

  Cenario: Validar resposta da API Get All Products List
    Dado que a API esta configurada com authentication
    Quando eu envio uma requisicao GET para o endpoint "api/brandsList"
    Entao o codigo de resposta deve ser 200
