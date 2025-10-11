package steps.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.*;
import pages.api.ApiTestPage;

public class ApiTestSteps {

    private final ApiTestPage apiTestPage = new ApiTestPage();

    @Dado("que a API esta configurada (com|sem) authentication e com ([^\"]*)$")
    public void queAAPIDeProdutosEstaDisponivel(String with, String contentType) {
        apiTestPage.configApiHelper(with.equalsIgnoreCase("com"), contentType);
    }

    @Quando("eu envio uma requisicao (GET|POST|PUT|PATCH|DELETE) para o endpoint ([^\"]*)$")
    public void euEnvioUmaRequisicaoGETParaAListaDeProdutos(String method, String endpoint) {
        apiTestPage.request(method, endpoint);
    }

    @E("configuro a api de usuario com a seguinte lista de colunas:")
    public void configuroAApiDeUsuarioComASeguinteListaDeColunas(DataTable dataTable) {
        apiTestPage.insertDataTable(dataTable);
    }

    @Entao("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        apiTestPage.validateStatusCode(statusCode);
    }

    @E("o response code de resposta da api deve ser {int}")
    public void oResponseCodeDaRespostaDeveSer(int responseCode) {
        apiTestPage.validateResponseCode(responseCode);
    }

    @E("valido o texto {string} no parametro {string}")
    public void validoOTextoNoParametro(String text, String parameter) {
        apiTestPage.valideTextParameter(text, parameter);
    }

    @E("configuro a api de usuario com o ultimo payload enviado")
    public void configuroAApiDeUsuarioComOUltimoPayloadEnviado() {
        apiTestPage.preparePayloadAgain();
    }
}
