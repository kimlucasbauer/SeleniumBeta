package steps.api;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.api.ApiTestPage;

public class ApiTestSteps {

    private final ApiTestPage apiTestPage = new ApiTestPage();

    @Dado("que a API esta configurada (com|sem) authentication$")
    public void queAAPIDeProdutosEstaDisponivel(String with) {
        apiTestPage.configApiHelper(with.equalsIgnoreCase("com"));
    }

    @Quando("eu envio uma requisicao {word} para o endpoint {string}")
    public void euEnvioUmaRequisicaoGETParaAListaDeProdutos(String method, String endpoint) {
        apiTestPage.request(method.toUpperCase(), endpoint);
    }

    @Entao("o codigo de resposta deve ser {int}")
    public void oCodigoDeRespostaDeveSer(int statusCode) {
        apiTestPage.validateStatusCode(statusCode);
    }

}
