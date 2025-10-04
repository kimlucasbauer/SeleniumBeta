package utils.dsl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.image.BufferedImage;
import java.time.Duration;

public class Wait extends Assertions {

    public void waitToBeVisable(Object locator) {
        waitToBeVisable(locator, TIMEOUT);
    }

    public void waitToBeVisable(Object locator, int time) {
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(webDriver -> {
                try {
                    redirectScreenToElement(bySelenium(locator));
                    return getDriver().findElement(bySelenium(locator)).isDisplayed();
                } catch (Exception e) {
                    return null;
                }
            });
        } catch (Exception e) {
            fail("Element " + locator + " dont load", e.getMessage());
        }
    }

    public void waitToBeClickabe(Object locator) {
        waitToBeClickabe(locator, TIMEOUT);
    }

    public void waitToBeClickabe(Object locator, int time) {
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(webDriver -> {
                try {
                    redirectScreenToElement(bySelenium(locator));
                    return getDriver().findElement(bySelenium(locator)).isEnabled();
                } catch (Exception e) {
                    return null;
                }
            });
        } catch (Exception e) {
            fail("Element " + locator + " dont load", e.getMessage());
        }
    }

    public void waitAlertToBeVisable() {
        waitAlertToBeVisable(TIMEOUT);
    }

    public void waitAlertToBeVisable(int time) {
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            fail("Alert dont load", e.getMessage());
        }
    }

    public void waitImageLoad(Object locatorEnum, String fileName, int time) {
        waitImageLoad(locatorEnum, getImageInFolder(fileName), time);
    }

    public void waitImageLoad(Object locator, BufferedImage expectedImage, int time) {
        try {
            (new WebDriverWait(getDriver(), Duration.ofSeconds(time))).until(webDriver -> {
                try {
                    redirectScreenToElement(locator);
                    WebElement element = getDriver().findElement(bySelenium(locator));
                    BufferedImage actualImage = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1)).takeScreenshot(getDriver(), element).getImage();
                    ImageDiffer imgDiff = new ImageDiffer();
                    ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);
                    return diff.hasDiff() ? null : true;
                } catch (Exception e) {
                    return null;
                }
            });
        } catch (Exception e) {
            fail("Element " + locator + " dont load", e.getMessage());
        }
    }

    public boolean waitImageLoad(Object imgLocator, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));
        WebElement imageElement = getDriver().findElement(bySelenium(imgLocator));

        ExpectedCondition<Boolean> imageLoaded = drv -> (Boolean) getJavascriptExecutor().executeScript(
                "return arguments[0].complete && " +
                        "typeof arguments[0].naturalWidth != 'undefined' && " +
                        "arguments[0].naturalWidth > 0", imageElement);

        try {
            return wait.until(imageLoaded);
        } catch (Exception e) {
            return false;
        }
    }
}
