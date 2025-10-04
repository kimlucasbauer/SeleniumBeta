# language: pt

Funcionalidade: Login

  Cenario: Realizar login no site
    Dado que estou na pagina de login
    Quando preencho os campos usuario e senha
    E clico no botao Login
    Entao a pagina inicial deve ser apresentada

  @Final
  Cenario: Realizar logout no site
    Dado que estou logado na plataforma
    Quando realizar o logout do sistema
    Entao a pagina de login deve ser apresentada