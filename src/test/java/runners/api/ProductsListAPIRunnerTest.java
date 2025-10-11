package runners.api;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/api/productsListAPI.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value =
        "pretty," +
                "html:target/reports/features/api/productsListAPI.html,"
                + "json:target/reports/features/api/productsListAPI.json,"
                + "junit:target/reports/features/api/productsListAPI.xml")
public class ProductsListAPIRunnerTest {
}