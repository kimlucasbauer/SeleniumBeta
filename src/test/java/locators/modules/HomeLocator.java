package locators.modules;

import locators.LocatorDefault;
import utils.enums.LocatorTypes;

public enum HomeLocator implements LocatorDefault {

    CAROUSEL("slider", LocatorTypes.ID);

    private final String val;
    private final LocatorTypes type;

    HomeLocator(String value, LocatorTypes locatorTypes) {
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
