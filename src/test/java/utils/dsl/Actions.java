package utils.dsl;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import utils.Utils;

import static utils.Utils.removeLateralSpaces;

public class Actions extends Checks {

    public void click(Object locator) {
        try {
            getDriver().findElement(bySelenium(locator)).click();
        } catch (Exception e) {
            waitToBeVisable(locator);
            waitToBeClickabe(locator);
            getDriver().findElement(bySelenium(locator)).click();
        }
    }

    public void type(Object locator, Object value) {
        try {
            getDriver().findElement(bySelenium(locator)).sendKeys(value.toString());
        } catch (Exception e) {
            waitToBeVisable(locator);
            waitToBeClickabe(locator);
            getDriver().findElement(bySelenium(locator)).sendKeys(value.toString());
        }
    }

    public String getValue(Object locator) {
        try {
            return getDriver().findElement(bySelenium(locator)).getAttribute("value");
        } catch (Exception e) {
            waitToBeVisable(locator);
            waitToBeClickabe(locator);
            return getDriver().findElement(bySelenium(locator)).getAttribute("value");
        }
    }

    public String getText(Object locator) {
        try {
            return removeLateralSpaces(getDriver().findElement(bySelenium(locator)).getText());
        } catch (Exception e) {
            waitToBeVisable(locator);
            waitToBeClickabe(locator);
            return removeLateralSpaces(getDriver().findElement(bySelenium(locator)).getText());
        }
    }

    public String getAttribute(Object locator, String attr) {
        try {
            return getDriver().findElement(bySelenium(locator)).getAttribute(attr);
        } catch (Exception e) {
            waitToBeVisable(locator);
            waitToBeClickabe(locator, TIMEOUT);
            return getDriver().findElement(bySelenium(locator)).getAttribute(attr);
        }
    }

    public void setURL(String newURL) {
        getDriver().get(newURL);
    }

    public void returnToHomePage() {
        this.setURL(url);
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void uploadFile(Object locator, String path) {
        if (!path.contains("/") && !path.contains("\\")) {
            path = "\\src\\test\\java\\utils\\repository\\" + path;
        }
        WebElement input;
        try {
            input = getDriver().findElement(bySelenium(locator));
        } catch (Exception e) {
            waitToBeClickabe(locator);
            input = getDriver().findElement(bySelenium(locator));
        }
        input.sendKeys(Utils.getUserDir() + path);
    }

    public String getTextAlert() {
        waitAlertToBeVisable();
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        waitAlertToBeVisable();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }
}
