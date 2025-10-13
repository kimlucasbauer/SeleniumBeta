package hooks;

import io.cucumber.java.Scenario;
import utils.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.dsl.Actions;

import static utils.Utils.getLastNameInBars;
import static utils.Utils.getStringDelimiter;

public class HooksCucumber {

    Actions dsl = new Actions();

    @Before
    public void beforeScenario(Scenario scenario) {
        if (!scenario.getUri().toString().contains("/api/")) {
            DriverFactory.getDriver();
        }
    }

    @After("@Final")
    public void quitDriver() {
        DriverFactory.quitDriver();
    }

    @After
    public void checkScenarioFail(Scenario scenario) {
        if (!DriverFactory.isDriverNull()) {
            String status = scenario.getStatus().name();

            if (!status.equalsIgnoreCase("PASSED")) {
                String feature = getLastNameInBars("\\" + scenario.getUri().toString().replace("classpath:", ""));
                feature = getStringDelimiter(feature, ".", 0);
                dsl.printScenarioFailed(
                        feature.replaceAll("\"", "")
                                + "_" + scenario.getName().replaceAll("\"", "")
                );
            }
        }
    }
}
