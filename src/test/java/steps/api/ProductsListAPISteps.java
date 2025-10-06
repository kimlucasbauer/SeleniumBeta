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

    @Quando("eu envio uma requisicao GET para a lista de produtos")
    public void euEnvioUmaRequisicaoGETParaAListaDeProdutos() {
        productsListAPIPage.getApiProductList();
    }

    @Entao("o codigo de resposta da api productList deve ser {int}")
    public void oCodigoDeRespostaDeveSer(int statusCode) {
        productsListAPIPage.validateStatusCode(statusCode);
    }

    @E("o corpo da resposta deve conter a lista de produtos")
    public void oCorpoDaRespostaDeveConterAListaDeProdutos() {
        productsListAPIPage.validateProductsListResponse();
    }
}
