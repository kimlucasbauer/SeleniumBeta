package utils.helpers;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.DriverFactory;

public class APIHelper {

    private RequestSpecification requestSpecification;
    private String json = "{}";
    private Response response;

    public APIHelper() {
        prepareURLForAPI(DriverFactory.url);
    }

    public void insertJson(String json) {
        this.json = json;
    }

    public void clearJson() {
        this.json = "{}";
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
    }

    public void prepareHeaderForAPI(String key, String value) {
        requestSpecification.header(key, value);
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
        response = requestSpecification.body(json).post(endpoint);
    }

    public void GET(String endpoint) {
        response = requestSpecification.body(json).get(endpoint);
    }

    public void PUT(String endpoint) {
        response = requestSpecification.body(json).put(endpoint);
    }

    public void DELETE(String endpoint) {
        response = requestSpecification.body(json).delete(endpoint);
    }

    public void PATCH(String endpoint) {
        response = requestSpecification.body(json).patch(endpoint);
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