package locators.modules;

import locators.LocatorDefault;
import utils.Utils;
import utils.enums.LocatorTypes;

public enum CartsLocator implements LocatorDefault {

    IMG_ITEM(getItemTR() + "//img", LocatorTypes.XPATH),
    NAME_ITEM(getItemTR() + "//*[@class=\"cart_description\"]/h4", LocatorTypes.XPATH),
    DESC_ITEM(getItemTR() + "//*[@class=\"cart_description\"]/p", LocatorTypes.XPATH),
    AMOUNT_ITEM(getItemTR() + "//*[@class=\"cart_quantity\"]/button", LocatorTypes.XPATH),
    TOTAL_PRICE_ITEM(getItemTR() + "//p[@class=\"cart_total_price\"]", LocatorTypes.XPATH),
    TR_TABLE("//tbody//tr", LocatorTypes.XPATH),
    DELETE_ITEM_CART_BUTTON("//tr[POS]//a[@class=\"cart_quantity_delete\"]", LocatorTypes.XPATH),
    CART_LIST_EMPTY("//p[normalize-space()=\"Cart is empty! Click here to buy products.\"]", LocatorTypes.XPATH),
    CHECKOUT_BUTTON("//a[normalize-space()=\"Proceed To Checkout\"]", LocatorTypes.XPATH),
    SECTION_DELIVERY_ADDRESS(getAddressDelivery() + getSectionAddress(), LocatorTypes.XPATH),
    FIRST_LAST_NAME_DELIVERY_ADDRESS(getAddressDelivery() + getFirstLastNameAddress(), LocatorTypes.XPATH),
    COUNTRY_NAME_DELIVERY_ADDRESS(getAddressDelivery() + getCountryNameAddress(), LocatorTypes.XPATH),
    PHONE_DELIVERY_ADDRESS(getAddressDelivery() + getPhoneAddress(), LocatorTypes.XPATH),
    SECTION_INVOICE_ADDRESS(getAddressInvoice() + getSectionAddress(), LocatorTypes.XPATH),
    FIRST_LAST_NAME_INVOICE_ADDRESS(getAddressInvoice() + getFirstLastNameAddress(), LocatorTypes.XPATH),
    COUNTRY_NAME_INVOICE_ADDRESS(getAddressInvoice() + getCountryNameAddress(), LocatorTypes.XPATH),
    PHONE_INVOICE_ADDRESS(getAddressInvoice() + getPhoneAddress(), LocatorTypes.XPATH),
    COMMENT_TEXTAREA("//label[normalize-space()=\"If you would like to add a comment about your order, please write it in the field below.\"]/../textarea", LocatorTypes.XPATH),
    PLACE_ORDER_BUTTON("//*[@href=\"/payment\"]", LocatorTypes.XPATH),
    HEAD_PAYMENT_STEP("//h2[normalize-space()=\"Payment\"]", LocatorTypes.XPATH),
    NAME_CARD_INPUT(Utils.inputDataQA("name-on-card"), LocatorTypes.XPATH),
    CARD_NUMBER_INPUT(Utils.inputDataQA("card-number"), LocatorTypes.XPATH),
    CVC_INPUT(Utils.inputDataQA("cvc"), LocatorTypes.XPATH),
    EXPIRATION_MONTH_INPUT(Utils.inputDataQA("expiry-month"), LocatorTypes.XPATH),
    EXPIRATION_YEAR_INPUT(Utils.inputDataQA("expiry-year"), LocatorTypes.XPATH),
    CONFIRM_BUTTON("//button[@data-qa=\"pay-button\"]", LocatorTypes.XPATH),
    CONGRATULATIONS_SUCCESS_MESSAGE("//*[@data-qa=\"order-placed\"]/following-sibling::p", LocatorTypes.XPATH);

    private final String val;
    private final LocatorTypes type;

    CartsLocator(String value, LocatorTypes locatorTypes) {
        val = value;
        type = locatorTypes;
    }

    @Override
    public String getValue() {
        return val;
    }

    @Override
    public LocatorTypes getType() {
        return type;
    }

    private static String getItemTR() {
        return "//*[@class=\"cart_price\"]//p[normalize-space()=\"ITEM\"]/ancestor::tr";
    }

    private static String getAddressDelivery() {
        return "//*[@data-qa=\"checkout-info\"]//*[@id=\"address_delivery\"]";
    }

    private static String getAddressInvoice() {
        return "//*[@data-qa=\"checkout-info\"]//*[@id=\"address_invoice\"]";
    }

    private static String getSectionAddress() {
        return "//h3[@class=\"page-subheading\"]";
    }

    private static String getFirstLastNameAddress() {
        return "//li[contains(@class, \"firstname\") and contains(@class, \"lastname\")]";
    }

    private static String getCountryNameAddress() {
        return "//li[contains(@class, \"country_name\")]";
    }

    private static String getPhoneAddress() {
        return "//li[contains(@class, \"phone\")]";
    }
}
