package utils.dsl;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.DriverFactory;
import utils.Utils;
import utils.enums.LocatorTypes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Functions extends DriverFactory {

    public By bySelenium(Object locator) {
        if (locator instanceof Enum<?> loc) {
            return switch (getTypeEnum(loc)) {
                case ID -> By.id(getValueEnum(loc));
                case XPATH -> By.xpath(getValueEnum(loc));
                case CSS -> By.cssSelector(getValueEnum(loc));
                default -> null;
            };
        } else if (locator instanceof String) return By.xpath(locator.toString());
        return null;
    }

    public LocatorTypes getTypeEnum(Enum<?> enumLocator) {
        try {
            return (LocatorTypes) Objects.requireNonNull(getMethod(enumLocator.getDeclaringClass(), "getType")).invoke(enumLocator);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Method getMethod(Class<?> class2, String method, Class<?>... params) {
        try {
            return class2.getDeclaredMethod(method, params);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValueEnum(Enum<?> enumLocator) {
        try {
            return (String) Objects.requireNonNull(getMethod(enumLocator.getDeclaringClass(), "getValue")).invoke(enumLocator);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void redirectScreenToElement(Object locator) {
        try {
            WebElement element = getDriver().findElement(bySelenium(locator));
            new org.openqa.selenium.interactions.Actions(getDriver())
                    .moveToElement(element)
                    .perform();
        } catch (Exception ignored) {
        }
    }

    public String getLocalStorageItem(String key) {
        return (String) getJavascriptExecutor().executeScript("return window.localStorage.getItem(arguments[0]);", key);
    }

    public void ajaxInject() {
        if (!(Boolean) getJavascriptExecutor().executeScript("return (typeof jQuery != 'undefined')")) {
            getJavascriptExecutor().executeScript("var headID = document.getElementsByTagName('head')[0];" +
                    "var newScript = document.createElement('script');" +
                    "newScript.type = 'text/javascript';" +
                    "newScript.src = 'https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js';" +
                    "headID.appendChild(newScript);");
            WebDriverWait waitJQ = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            Function<WebDriver, Boolean> JQueryAvailable = WebDriver -> (
                    (Boolean) getJavascriptExecutor().executeScript("return (typeof jQuery != 'undefined')")
            );
            waitJQ.until(JQueryAvailable);
            pause(1);
        }
    }

    public BufferedImage getImageInFolder(String name) {
        File file = new File("src/test/java/utils/repository/" + Utils.normalizeStringForFileName(name) + ".png");
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Screenshot printElement(Object locatorEnum) {
        ajaxInject();
        WebElement image = getDriver().findElement(bySelenium(locatorEnum));
        return new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(getDriver(), image);
    }

    public void saveImageFile(BufferedImage image, String path) {
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            throw new RuntimeException(path, e);
        }
    }

    public void printScenarioFailed(String nameFile) {
        ajaxInject();
        BufferedImage img;
        try {
            img = ImageIO.read(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE));
            boolean ignored = new File("errors").mkdir();
            ImageIO.write(img, "png", new File("errors/" + nameFile + ".png"));
            System.out.println("File " + nameFile + " Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countElements(Object locatorEnum) {
        List<WebElement> rows = getDriver().findElements(bySelenium(locatorEnum));
        return rows.size();
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
