package pages.api;

import io.restassured.path.json.JsonPath;
import pages.GeneralPage;

public class ApiTestPage extends GeneralPage {

    private static JsonPath jsonPath;

    public void configApiHelper(boolean auth) {
        apiHelper.prepareHeaderForAPI(auth);
    }

    public void request(String method, String endpoint) {
        apiHelper.sendRequestToEndpoint(method, endpoint);
        jsonPath = apiHelper.getJsonPath();
    }

    public void validateStatusCode(int statusCode) {
        apiHelper.validateStatusCode(statusCode);

        int responseCode = jsonPath.getInt("responseCode");
        dsl.compare(statusCode, responseCode);
    }

}
