package pages;

import utils.dsl.Actions;
import utils.helpers.APIHelper;

public class GeneralPage {

    protected final Actions dsl = new Actions();
    protected APIHelper apiHelper = new APIHelper();

    public void validateTextOnElement(Object locator, String expected) {
        String actual = dsl.getText(locator);
        dsl.compare(expected, actual);
    }
}
