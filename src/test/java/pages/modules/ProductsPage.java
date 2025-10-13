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
                dsl.click(ADD_PRODUCT_BUTTON.setValue("ITEM", item.get("item")).replace("itemName", item.get("nome item")));
                dsl.click(CONTINUE_SHOPING_BUTTON);
            }
        }
    }
}
