# SeleniumBeta

Seja bem vindo ao projeto Automação de testes em Selenium com Java 25!

Aqui será apresentado como configurar o projeto e uma breve explicação de como está estruturado para podermos usa-lo.

* Instale o Java JDK 25 e compile o Maven para instalação das dependências do projeto;
* Instale os plugins Cucumber for Java e Cucumber Scenarios Indexer para o Intellij ou o equivalente para a IDE que
  estiver utilizando;

Configuração finalizada!, agora é só selecionar o Runner correspondente a feauture que você quiser rodar e executar com o jUnit.

Ao final será gerado um link do relatório do cucumber no próprio console de execução e também o relatório do jUnit em [Reports](target\reports\features) dentro da pasta target!.

## Estrutura

Antes de começarmos, é recomendado que seja visto todas as classes da [DSL](src/test/java/utils/dsl) para ciência de
quais métodos já estão preparados para uso;

E também entendermos o propósito dos seguintes arquivos:

<span><span style="color: rgb(255, 104, 74)">Runners</span> → Onde o jUnit será configurado para gerar relatórios e
chamar o arquivo feature específico.</span>

<span><span style="color: rgb(0, 176, 38)">Feature</span> → Onde será escrito os cenários Gherkin e será implementado no
arquivo Steps.</span>

<span><span style="color: rgb(255, 196, 0)">Steps</span> → Onde será implementado as linhas escritas no arquivo Feature
e irá chamar os métodos do arquivo Page.</span>

<span><span style="color: rgb(0, 242, 255)">Pages</span> → Onde será implementado em Java as ações do Selenium para
interagir com a página.</span>

<span><span style="color: rgb(222, 0, 211)">Locators</span> → Arquivo auxiliar ao arquivo Page, pois irá armazenar todos
os locators utilizados para as ações.</span>

<span><span style="color: rgb(94, 135, 32)">Hooks</span> → Arquivo onde será configurado os hooks do Cucumber e jUnit
para execução de métodos programada.</span>

## Exemplo para implementar um novo teste

1. Crie seu arquivo <b>nomeDaFuncionalidade.<span style="color: rgb(0, 176, 38)">feature</span></b> em [
   `SeleniumBeta\src\test\resources\features`](src/test/resources/features).

2. Edite seu arquivo feature com o seguinte padrão:

<div style="background-color: rgb(181, 181, 181); border-radius: 1rem; text-shadow: 1px 1px 1px black;">
        <p style="color: rgb(80, 79, 79);"># language: pt</p>
        <div style="padding-left: 1rem">
        <span>
            <b style="color: rgb(255, 115, 0);">Funcionalidade</b>: 
            <span style="color: rgb(0, 176, 38);">Nome da Funcionalidade</span>
        </span>
        <div style="padding-left: 3rem;">
            <br>
            <span>
                <b style="color: rgb(255, 115, 0);">Cenario</b>: 
                <span style="color: rgb(0, 176, 38);">Titulo do Cenario a ser realizado</span>
            </span>
            <br>
            <span>
                <b style="color: rgb(255, 115, 0);">Dado</b> 
                <a style="color: white;">que estou logado na plataforma</a>
            </span>
            <br>
            <span>
                <b style="color: rgb(255, 115, 0);">Quando</b> 
                <a style="color: white;">acessar o modulo 
                    <span style="color: rgb(116, 150, 247); font-weight: bold;">Cart</span>
                </a>
            </span>
            <br>
            <span>
                <b style="color: rgb(255, 115, 0);">E</b> 
                <a style="color: white;">excluir todos os itens adicionados ao carrinho de compras</a>
            </span>
            <br>
            <span>
                <b style="color: rgb(255, 115, 0);">Entao</b> 
                <a style="color: white;">a lista de compras devera estar vazia</a>
            </span>
        </div>
    </div>
</div>
<br>

3. Cadastre o arquivo <b>NomeDaFuncionalidade<span style="color: rgb(255, 196, 0)">Steps</span>.java</b> em [`SeleniumBeta\src\test\java\steps`](src/test/java/steps).

4. Edite o arquivo <b>steps</b> utilizando o seguinte padrão:

Exemplo:

```JAVA
package steps.modules;

import io.cucumber.java.pt.Quando;
import pages.modules.HomePage;

public class HomeSteps {

    private final HomePage homePage = new HomePage();

    @Quando("acessar o modulo (Contact Us|Products|Cart)$")
    public void acessarOModuloContactUs(String module) {
        homePage.navigateToPage(module.toUpperCase().replaceAll(" ", "_"));
    }
}
```


5. Cadastre o arquivo <b>NomeDaFuncionalidade<span style="color: rgb(0, 242, 255)">Page</span>.java</b> em [`SeleniumBeta\src\test\java\pages`](src/test/java/pages).

6. Edite o arquivo <b>page</b> utilizando o seguinte padrão:

Exemplo:

```JAVA
package pages.modules;

import pages.GeneralPage;

import java.util.List;
import java.util.Map;

import static locators.modules.ProductsLocator.*;

public class ProductsPage extends GeneralPage {

    public List<Map<String, String>> itens;

    public void addItensToCart(List<Map<String, String>> itens) {
        this.itens = itens;
        for (Map<String, String> item : itens) {
            for (int i = 0; i < Integer.parseInt(item.get("quantidade")); i++) {
                dsl.click(ADD_PRODUCT_BUTTON.setValue("ITEM", item.get("item")));
                dsl.click(CONTINUE_SHOPING_BUTTON);
            }
        }
    }
}
```

7. Cadastre o arquivo <b>NomeDaFuncionalidade<span style="color: rgb(222, 0, 211)">Locator</span>.java</b> em [`SeleniumBeta\src\test\java\locators`](src/test/java/locators).

8. Edite o arquivo <b>locator</b> utilizando o seguinte padrão:

Exemplo:

```JAVA
package locators.others;

import locators.LocatorDefault;
import utils.Utils;
import utils.enums.LocatorTypes;

public enum LoginLocator implements LocatorDefault {

    EMAIL_INPUT(Utils.inputDataQA("login-email"), LocatorTypes.XPATH),
    PASSWORD_INPUT(Utils.inputDataQA("login-password"), LocatorTypes.XPATH),
    LOGIN_BUTTON("//button[@data-qa=\"login-button\"]", LocatorTypes.XPATH);

    private final String val;
    private final LocatorTypes type;

    LoginLocator(String value, LocatorTypes locatorTypes) {
        this.val = value;
        this.type = locatorTypes;
    }

    @Override
    public String getValue() {
        return val;
    }

    @Override
    public LocatorTypes getType() {
        return type;
    }
}
```