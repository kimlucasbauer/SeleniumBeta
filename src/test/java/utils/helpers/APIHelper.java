package utils.helpers;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.DriverFactory;

import java.util.Map;

public class APIHelper {

    private RequestSpecification requestSpecification;
    private String json = "{}";
    private Map<String, String> formData;
    private Response response;
    public boolean contentTypeJson = false;

    public APIHelper() {
        prepareURLForAPI(DriverFactory.url);
    }

    public void insertData(String json) {
        this.json = json;
    }

    public void insertData(Map<String, String> json) {
        this.formData = json;
    }

    public void clearData() {
        if (contentTypeJson) {
            this.json = "{}";
        } else {
            this.formData = null;
        }
    }

    public void prepareURLForAPI(String environment) {
        RestAssured.baseURI = environment;
        requestSpecification = RestAssured.given();
    }

    public void prepareHeaderForAPI(boolean authorization) {
        if (authorization) {
            //requestSpecification.header("Authorization", "Bearer ");
        }
        requestSpecification.header("Content-Type", "application/json");
        contentTypeJson = true;
    }

    public void prepareHeaderForAPI(String key, String value) {
        requestSpecification.header(key, value);
        if (key.equals("Content-Type") && value.contains("json")) {
            contentTypeJson = true;
        }
    }

    public void sendRequestToEndpoint(String method, String endpoint) {
        switch (method) {
            case "POST":
                POST(endpoint);
                break;
            case "GET":
                GET(endpoint);
                break;
            case "PUT":
                PUT(endpoint);
                break;
            case "PATCH":
                PATCH(endpoint);
                break;
            case "DELETE":
                DELETE(endpoint);
                break;
        }
    }

    public void POST(String endpoint) {
        if (contentTypeJson) {
            response = requestSpecification.body(json).post(endpoint);
        } else {
            response = requestSpecification.formParams(formData).post(endpoint);
        }
    }

    public void GET(String endpoint) {
        if (contentTypeJson) {
            response = requestSpecification.body(json).get(endpoint);
        } else {
            response = requestSpecification.formParams(formData).get(endpoint);
        }
    }

    public void PUT(String endpoint) {
        if (contentTypeJson) {
            response = requestSpecification.body(json).put(endpoint);
        } else {
            response = requestSpecification.formParams(formData).put(endpoint);
        }
    }

    public void DELETE(String endpoint) {
        if (contentTypeJson) {
            response = requestSpecification.body(json).delete(endpoint);
        } else {
            response = requestSpecification.formParams(formData).delete(endpoint);
        }
    }

    public void PATCH(String endpoint) {
        if (contentTypeJson) {
            response = requestSpecification.body(json).patch(endpoint);
        } else {
            response = requestSpecification.formParams(formData).patch(endpoint);
        }
    }

    public JsonPath getJsonPath() {
        return JsonPath.from(response.asString());
    }

    public Response getResponse() {
        return response;
    }

    public <T> T responseHasParameter(TypeRef<T> typeRef) {
        return response.as(typeRef);
    }

    public void validateStatusCode(int expectedValue) {
        Assert.assertEquals(expectedValue, response.statusCode());
    }

}