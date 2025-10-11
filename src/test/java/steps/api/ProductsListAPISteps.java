package steps.api;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.api.ProductsListAPIPage;

public class ProductsListAPISteps {

    private final ProductsListAPIPage productsListAPIPage = new ProductsListAPIPage();

    @Dado("que a API de produtos esta disponivel")
    public void queAAPIDeProdutosEstaDisponivel() {
        productsListAPIPage.configApiHelper();
    }

    @Quando("eu envio uma requisicao (GET|DELETE) para a lista de produtos$")
    public void euEnvioUmaRequisicaoGETParaAListaDeProdutos(String method) {
        switch (method) {
            case "GET":
                productsListAPIPage.getApiProductList();
                break;
            case "DELETE":
                productsListAPIPage.deleteApiProductList();
                break;
        }
    }

    @Entao("o status code da resposta da api productList deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        productsListAPIPage.validateStatusCode(statusCode);
    }

    @E("o response code de resposta da api productList deve ser {int}")
    public void oResponseCodeDaRespostaDeveSer(int responseCode) {
        productsListAPIPage.validateResponseCode(responseCode);
    }

    @E("valido o response header da api de lista de produtos")
    public void validoOResponseHeaderDaApiDeListaDeProdutos() {
        productsListAPIPage.validateResponseHeader();
    }

    @E("o corpo da resposta deve conter a lista de produtos")
    public void oCorpoDaRespostaDeveConterAListaDeProdutos() {
        productsListAPIPage.validateProductsListResponse();
    }
}
