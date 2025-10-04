package locators.modules;

import locators.LocatorDefault;
import utils.enums.LocatorTypes;

public enum ProductsLocator implements LocatorDefault {

    ADD_PRODUCT_BUTTON("//*[contains(@class,\"productinfo\")]//h2[normalize-space()=\"ITEM\"]/following-sibling::a", LocatorTypes.XPATH),
    CONTINUE_SHOPING_BUTTON("//button[normalize-space()=\"Continue Shopping\"]", LocatorTypes.XPATH);

    private final String val;
    private final LocatorTypes type;

    ProductsLocator(String value, LocatorTypes locatorTypes) {
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
}
