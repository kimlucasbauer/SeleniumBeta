# language: pt

Funcionalidade: Products e Carts

  Cenario: Garantir carrinho de compra vazio
    Dado que estou logado na plataforma
    Quando acessar o modulo Cart
    E excluir todos os itens adicionados ao carrinho de compras
    Entao a lista de compras devera estar vazia

  Cenario: Adicionar itens ao carrinho de compras
    Dado que estou logado na plataforma
    Quando acessar o modulo Products
    E adicionar os seguintes itens ao carrinho:
      | item     | nome item     | descricao     | quantidade |
      | Rs. 500  | Blue Top      | Women > Tops  | 2          |
      | Rs. 400  | Men Tshirt    | Men > Tshirts | 3          |
      | Rs. 1500 | Stylish Dress | Women > Dress | 1          |
    E acessar o modulo Cart
    Entao valido o carrinho de compras completo

  @Final
  Cenario: Realizar checkout nos itens adicionados ao carrinho
    Dado que estou logado na plataforma
    Quando acessar o modulo Cart
    E preecher o comentario na etapa de review "teste obs"
    Entao valido a etapa review do carrinho de compras
    E clico no botao Place order para avancar a compra no carrinho
    E valido que a etapa de pagamento foi apresentado
    Quando preencher a etapa de pagamento com as seguintes informacoes:
      | nome  | numero              | cvc | mes | ano  |
      | teste | 1111 1111 1111 1111 | 123 | 1   | 2100 |
    E clicar em confirmar pagamento no carrinho de compras
    Entao uma mensagem de confirmacao de pagamento devera ser apresentada