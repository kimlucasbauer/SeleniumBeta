# language: pt

Funcionalidade: Contact us

  @Final
  Cenario: Entrar em contato com o suporte
    Dado que estou logado na plataforma
    Quando acessar o modulo Contact Us
    E preencher todos os campos do formulario de contato com o suporte
    E enviar o formulario de contato com o suporte
    Entao um alerta de confirmacao do envio do formulario deve ser apresentado
    E uma mensagem de sucesso devera aparecer ao final do contato com o suporte