package utils.dsl;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

import static utils.Utils.removeLateralSpaces;

public class Checks extends Wait {

    public Boolean isEnabled(Object locator) {
        try {
            return getDriver().findElement(bySelenium(locator)).isEnabled();
        } catch (Exception e) {
            waitToBeClickabe(locator, TIMEOUT);
            return getDriver().findElement(bySelenium(locator)).isEnabled();
        }
    }

    public Boolean isFilled(Object locator) {
        String txt = removeLateralSpaces(getDriver().findElement(bySelenium(locator)).getText());
        return !txt.isBlank();
    }

    public Boolean checkBeClickable(Object locator) {
        return checkBeClickable(locator, TIMEOUT);
    }
    public Boolean checkBeClickable(Object locator, int time) {
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(webDriver -> {
                try {
                    redirectScreenToElement(locator);
                    return getDriver().findElement(bySelenium(locator)).isEnabled();
                } catch (Exception e) {
                    return null;
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return true when the first condition is valid or false when the second is valid
     */
    public Boolean doubleCheck(Object locatorCondition1, Object locatorCondition2, int time) {
        AtomicBoolean checkCondition1 = new AtomicBoolean(false);
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(webDriver -> {
                try {
                    if (checkBeClickable(locatorCondition1, 1)) {
                        checkCondition1.set(true);
                    }
                    if (checkBeClickable(locatorCondition2, 1)) {
                        checkCondition1.set(false);
                        return true;
                    } else if (!checkCondition1.get()) return null;
                    else return true;
                } catch (Exception e) {
                    return null;
                }
            });
        } catch (Exception e) {
            fail("Elements " + locatorCondition1 + " and " + locatorCondition2 + " dont load", e.getMessage());
        }
        return checkCondition1.get();
    }
}
