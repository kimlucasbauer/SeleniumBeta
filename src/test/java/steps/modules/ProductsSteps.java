package steps.modules;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.E;
import pages.modules.ProductsPage;

import java.util.List;
import java.util.Map;

public class ProductsSteps {

    public static final ProductsPage productsPage = new ProductsPage();

    @E("adicionar os seguintes itens ao carrinho:")
    public void adicionarOsSeguintesItensAoCarrinho(DataTable dataTable) {
        List<Map<String, String>> itens = dataTable.asMaps(String.class, String.class);
        productsPage.addItensToCart(itens);
    }
}
