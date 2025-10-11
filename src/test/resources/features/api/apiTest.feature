# language: pt

Funcionalidade: Validar api com cenarios imperativos

  Esquema do Cenario: Cadastrar multiplos usuarios via API
    Dado que a API esta configurada sem authentication e com application/x-www-form-urlencoded
    E configuro a api de usuario com a seguinte lista de colunas:
      | name          | <name>          |
      | email         | <email>         |
      | password      | <password>      |
      | title         | <title>         |
      | birth_date    | <birth_date>    |
      | birth_month   | <birth_month>   |
      | birth_year    | <birth_year>    |
      | firstname     | <firstname>     |
      | lastname      | <lastname>      |
      | company       | <company>       |
      | address1      | <address1>      |
      | address2      | <address2>      |
      | country       | <country>       |
      | zipcode       | <zipcode>       |
      | state         | <state>         |
      | city          | <city>          |
      | mobile_number | <mobile_number> |
    Quando eu envio uma requisicao POST para o endpoint api/createAccount
    Entao o status code da resposta deve ser 200
    E o response code de resposta da api deve ser 201
    E valido o texto "User created!" no parametro "message"

    Exemplos:
      | name | email                 | password | title | birth_date | birth_month | birth_year | firstname | lastname | company  | address1            | address2 | country | zipcode   | state          | city          | mobile_number  |
      | kim  | kim@automacaobeta.com | 123456   | Mr    | 10         | 10          | 1990       | Kim       | Silva    | QA       | Rua das Flores, 123 | Apto 45  | Brazil  | 88000-000 | Santa Catarina | Florianópolis | +5548999999999 |
      | ana  | ana@automacaobeta.com | 654321   | Miss  | 5          | 6           | 1985       | Ana       | Souza    | DevHouse | Av. Central, 456    | Sala 12  | Brazil  | 88010-000 | São Paulo      | São Paulo     | +5511999999999 |


  Cenario: Validar mensagem de email ja existente
    Dado que a API esta configurada sem authentication e com application/x-www-form-urlencoded
    E configuro a api de usuario com o ultimo payload enviado
    Quando eu envio uma requisicao POST para o endpoint api/createAccount
    Entao o status code da resposta deve ser 200
    E o response code de resposta da api deve ser 400
    E valido o texto "Email already exists!" no parametro "message"


  Esquema do Cenario: Deletar multiplos usuarios via API
    Dado que a API esta configurada sem authentication e com application/x-www-form-urlencoded
    E configuro a api de usuario com a seguinte lista de colunas:
      | email    | <email>    |
      | password | <password> |
    Quando eu envio uma requisicao DELETE para o endpoint api/deleteAccount
    Entao o status code da resposta deve ser 200
    E o response code de resposta da api deve ser 200
    E valido o texto "Account deleted!" no parametro "message"

    Exemplos:
      | email                 | password |
      | kim@automacaobeta.com | 123456   |
      | ana@automacaobeta.com | 654321   |