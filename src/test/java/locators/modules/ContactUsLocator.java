package locators.modules;

import locators.LocatorDefault;
import utils.Utils;
import utils.enums.LocatorTypes;

public enum ContactUsLocator implements LocatorDefault {
    NAME_INPUT(Utils.inputDataQA("name"), LocatorTypes.XPATH),
    EMAIL_INPUT(Utils.inputDataQA("email"), LocatorTypes.XPATH),
    SUBJECT_INPUT(Utils.inputDataQA("subject"), LocatorTypes.XPATH),
    MESSAGE_TEXTAREA("//textarea[@data-qa=\"message\"]", LocatorTypes.XPATH),
    UPLOAD_INPUT("//input[@type=\"file\"]", LocatorTypes.XPATH),
    SUBMIT_BUTTON(Utils.inputDataQA("submit-button"), LocatorTypes.XPATH),
    SUCCESS_MESSAGE_ELEMENT("//h2[normalize-space()=\"Get In Touch\"]/following-sibling::*[@class=\"status alert alert-success\"]", LocatorTypes.XPATH);

    private final String val;
    private final LocatorTypes type;

    ContactUsLocator(String value, LocatorTypes locatorTypes) {
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
