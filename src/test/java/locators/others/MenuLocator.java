package locators.others;

import locators.LocatorDefault;
import utils.enums.LocatorTypes;

public enum MenuLocator implements LocatorDefault {

    LOGOUT_BUTTON("//a[@href=\"/logout\" and normalize-space()=\"Logout\"]", LocatorTypes.XPATH, null),
    LOGGED_USER_ELEMENT("//a[normalize-space()=\"Logged in as USER\"]", LocatorTypes.XPATH, null),
    CONTACT_US("//a[@href=\"/contact_us\"]", LocatorTypes.XPATH, null),
    PRODUCTS("//a[@href=\"/products\"]", LocatorTypes.XPATH, null),
    CART("//a[@href=\"/view_cart\"]", LocatorTypes.XPATH, null);

    private final String val;
    private final LocatorTypes type;
    private final MenuLocator previousModule;

    MenuLocator(String value, LocatorTypes locatorTypes, MenuLocator previousModule) {
        val = value;
        type = locatorTypes;
        this.previousModule = previousModule;
    }

    @Override
    public String getValue() {
        return val;
    }

    @Override
    public LocatorTypes getType() {
        return type;
    }

    public MenuLocator getPreviousModule() {
        return previousModule;
    }
}
