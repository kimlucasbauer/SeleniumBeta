package pages;

import utils.dsl.Actions;

public class GeneralPage {

    protected final Actions dsl = new Actions();

    public void validateTextOnElement(Object locator, String expected) {
        String actual = dsl.getText(locator);
        dsl.compare(expected, actual);
    }
}
