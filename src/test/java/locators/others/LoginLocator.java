package locators.others;

import locators.LocatorDefault;
import utils.Utils;
import utils.enums.LocatorTypes;

public enum LoginLocator implements LocatorDefault {

    EMAIL_INPUT(Utils.inputDataQA("login-email"), LocatorTypes.XPATH),
    PASSWORD_INPUT(Utils.inputDataQA("login-password"), LocatorTypes.XPATH),
    LOGIN_BUTTON("//button[@data-qa=\"login-button\"]", LocatorTypes.XPATH);

    private final String val;
    private final LocatorTypes type;

    LoginLocator(String value, LocatorTypes locatorTypes) {
        this.val = value;
        this.type = locatorTypes;
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