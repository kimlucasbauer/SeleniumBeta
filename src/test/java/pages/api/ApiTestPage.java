package pages.api;

import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import pages.GeneralPage;
import utils.helpers.JsonGenerator;

import java.util.Map;

public class ApiTestPage extends GeneralPage {

    private static JsonPath jsonPath;
    private static Map<String, String> payloadData;

    public void configApiHelper(boolean auth, String contentType) {
        apiHelper.prepareHeaderForAPI("Content-Type", contentType);
        if (auth) {
            apiHelper.prepareHeaderForAPI("Authorization", "");
        }
    }

    public void request(String method, String endpoint) {
        apiHelper.sendRequestToEndpoint(method, endpoint);
        jsonPath = apiHelper.getJsonPath();
    }

    public void validateStatusCode(int statusCode) {
        apiHelper.validateStatusCode(statusCode);
    }

    public void validateResponseCode(int responseCode) {
        dsl.compare(responseCode, jsonPath.getInt("responseCode"));
    }

    public void insertDataTable(DataTable dataTable) {
        payloadData = dataTable.asMap();
        preparePayloadAgain();
    }

    public void valideTextParameter(String text, String parameter) {
        dsl.compare(text, jsonPath.get(parameter));
    }

    public void preparePayloadAgain() {
        if (apiHelper.contentTypeJson) {
            JsonGenerator jsonGenerator = new JsonGenerator();
            jsonGenerator.add(payloadData);
            apiHelper.insertData(jsonGenerator.buildJson());
        } else {
            apiHelper.insertData(payloadData);
        }
    }

}
