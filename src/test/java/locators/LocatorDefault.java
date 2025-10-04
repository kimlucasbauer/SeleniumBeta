package locators;

import utils.enums.LocatorTypes;

public interface LocatorDefault {

    String getValue();

    LocatorTypes getType();

    default String setValue(String item, String newValue) {
        return getValue().replace(item, newValue);
    }

}
