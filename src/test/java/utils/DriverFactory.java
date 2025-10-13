package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.enums.Browsers;

import java.util.ArrayList;
import java.util.List;

public class DriverFactory {

    private static WebDriver driver;
    private static JavascriptExecutor jse;
    public static Browsers browser;
    public static String url = Utils.getEnvOrDefault("URL", "https://automationexercise.com");
    public static Boolean headless = Boolean.parseBoolean(Utils.getEnvOrDefault("HEADLESS", "false"));
    public static Integer TIMEOUT = 90;

    public static JavascriptExecutor getJavascriptExecutor() {
        if (driver != null) {
            if (jse == null) {
                jse = (JavascriptExecutor) driver;
            }
            return jse;
        } else {
            throw new NullPointerException("WebDriver is null. Cannot use JavascriptExecutor.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = getBrowserDriver();
            assert driver != null;
            setResolution();
            driver.get(url + "/login");
        }
        return driver;
    }

    private static WebDriver getBrowserDriver() {
        String browserName = Utils.getEnvOrDefault("BROWSER", Browsers.CHROME.toString()).toUpperCase();

        List<String> argumentos = new ArrayList<>();
        argumentos.add("--verbose");
        argumentos.add("--no-sandbox");
        argumentos.add("--mute-audio");

        if (headless) argumentos.add("--headless");

        browser = Browsers.getBrowser(browserName);

        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(argumentos);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(argumentos);
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(edgeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(argumentos);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions);
        }

        return null;
    }

    public static void setResolution() {
        driver.manage().window().setSize(new Dimension(1382, 735));
        driver.manage().window().setPosition(new Point(-7, 0));
    }

    public static void main(String[] args) {
        try {
            getDriver().get(url);
            System.out.println();
        } finally {
            quitDriver();
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            jse = null;
        }
    }
}
