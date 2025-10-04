package pages.modules;

import pages.GeneralPage;
import steps.modules.ProductsSteps;

import java.util.List;
import java.util.Map;

import static locators.modules.CartsLocator.*;

public class CartsPage extends GeneralPage {

    private final List<Map<String, String>> itens = ProductsSteps.productsPage.itens;

    public void validateCartShopping() {
        for (Map<String, String> item : itens) {
            dsl.waitImageLoad(IMG_ITEM.setValue("ITEM", item.get("item")), 30);
            dsl.assertImage(IMG_ITEM.setValue("ITEM", item.get("item")), item.get("item"));
            int amount = 0;
            for (int i = 0; i < Integer.parseInt(item.get("quantidade")); i++) {
                amount += Integer.parseInt(item.get("item").replace("Rs. ", ""));
            }
            validateTextOnElement(NAME_ITEM.setValue("ITEM", item.get("item")), item.get("nome item"));
            validateTextOnElement(DESC_ITEM.setValue("ITEM", item.get("item")), item.get("descricao"));
            validateTextOnElement(AMOUNT_ITEM.setValue("ITEM", item.get("item")), item.get("quantidade"));
            validateTextOnElement(TOTAL_PRICE_ITEM.setValue("ITEM", item.get("item")), "Rs. " + amount);
        }
    }

    public void clearCartShopping() {
        int qt = dsl.countElements(TR_TABLE);
        for (; qt > 0; qt--) {
            dsl.click(DELETE_ITEM_CART_BUTTON.setValue("POS", String.valueOf(qt)));
        }
    }

    public void validateCartShoppingIsEmpty() {
        dsl.waitToBeVisable(CART_LIST_EMPTY);
    }

    public void validateReviewOrder() {
        String firstLastName = "Mr. Automation QA";
        String countryName = "United States";
        String phone = "23422342";

        validateTextOnElement(SECTION_DELIVERY_ADDRESS, "YOUR DELIVERY ADDRESS");
        validateTextOnElement(FIRST_LAST_NAME_DELIVERY_ADDRESS, firstLastName);
        validateTextOnElement(COUNTRY_NAME_DELIVERY_ADDRESS, countryName);
        validateTextOnElement(PHONE_DELIVERY_ADDRESS, phone);
        validateTextOnElement(SECTION_INVOICE_ADDRESS, "YOUR BILLING ADDRESS");
        validateTextOnElement(FIRST_LAST_NAME_INVOICE_ADDRESS, firstLastName);
        validateTextOnElement(COUNTRY_NAME_INVOICE_ADDRESS, countryName);
        validateTextOnElement(PHONE_INVOICE_ADDRESS, phone);

        validateCartShopping();
    }

    public void addCommentOrder(String obs) {
        dsl.click(CHECKOUT_BUTTON);
        dsl.type(COMMENT_TEXTAREA, obs);
    }

    public void clickOnPlaceOrderButton() {
        dsl.click(PLACE_ORDER_BUTTON);
    }

    public void validatePaymentStep() {
        dsl.waitToBeVisable(HEAD_PAYMENT_STEP);
        dsl.waitToBeVisable(NAME_CARD_INPUT);
        dsl.waitToBeVisable(CARD_NUMBER_INPUT);
        dsl.waitToBeVisable(CVC_INPUT);
        dsl.waitToBeVisable(EXPIRATION_MONTH_INPUT);
        dsl.waitToBeVisable(EXPIRATION_YEAR_INPUT);
        dsl.waitToBeVisable(CONFIRM_BUTTON);
    }

    public void insertDataForPayment(List<Map<String, String>> list) {
        for (Map<String, String> data : list) {
            dsl.type(NAME_CARD_INPUT, data.get("nome"));
            dsl.type(CARD_NUMBER_INPUT, data.get("numero"));
            dsl.type(CVC_INPUT, data.get("cvc"));
            dsl.type(EXPIRATION_MONTH_INPUT, data.get("mes"));
            dsl.type(EXPIRATION_YEAR_INPUT, data.get("ano"));

        }
    }

    public void clickOnConfirmPaymentButton() {
        dsl.click(CONFIRM_BUTTON);
    }

    public void validateSuccessMessage() {
        dsl.waitToBeVisable(CONGRATULATIONS_SUCCESS_MESSAGE);
        validateTextOnElement(CONGRATULATIONS_SUCCESS_MESSAGE, "Congratulations! Your order has been confirmed!");
    }

}
