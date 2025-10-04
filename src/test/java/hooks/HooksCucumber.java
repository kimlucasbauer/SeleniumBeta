package hooks;

import io.cucumber.java.Scenario;
import utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class HooksCucumber {
    @Before
    public void beforeScenario() {
        DriverFactory.getDriver();
    }

    @After("@Final")
    public void quitDriver(Scenario scenario) {
        DriverFactory.quitDriver();
    }
}
