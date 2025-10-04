package steps.modules;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.modules.CartsPage;

import java.util.List;
import java.util.Map;

public class CartsSteps {

    private final CartsPage cartsPage = new CartsPage();

    @Entao("valido o carrinho de compras completo")
    public void validoOCarrinhoDeComprasCompleto() {
        cartsPage.validateCartShopping();
    }

    @E("excluir todos os itens adicionados ao carrinho de compras")
    public void excluirTodosOsItensAdicionadosAoCarrinhoDeCompras() {
        cartsPage.clearCartShopping();
    }

    @Entao("a lista de compras devera estar vazia")
    public void aListaDeComprasDeveraEstarVazia() {
        cartsPage.validateCartShoppingIsEmpty();
    }

    @E("preecher o comentario na etapa de review {string}")
    public void preecherOComentarioNaEtapaDeReview(String obs) {
        cartsPage.addCommentOrder(obs);
    }

    @Entao("valido a etapa review do carrinho de compras")
    public void validoAEtapaReviewDoCarrinhoDeCompras() {
        cartsPage.validateReviewOrder();
    }

    @E("clico no botao Place order para avancar a compra no carrinho")
    public void clicoNoBotaoPlaceOrderParaAvancarACompraNoCarrinho() {
        cartsPage.clickOnPlaceOrderButton();
    }

    @E("valido que a etapa de pagamento foi apresentado")
    public void validoQueAEtapaDePagamentoFoiApresentado() {
        cartsPage.validatePaymentStep();
    }

    @Quando("preencher a etapa de pagamento com as seguintes informacoes:")
    public void preencherAEtapaDePagamentoComAsSeguintesInformacoes(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        cartsPage.insertDataForPayment(list);
    }

    @E("clicar em confirmar pagamento no carrinho de compras")
    public void clicarEmConfirmarPagamentoNoCarrinhoDeCompras() {
        cartsPage.clickOnConfirmPaymentButton();
    }

    @Entao("uma mensagem de confirmacao de pagamento devera ser apresentada")
    public void umaMensagemDeConfirmacaoDePagamentoDeveraSerApresentada() {
        cartsPage.validateSuccessMessage();
    }
}
