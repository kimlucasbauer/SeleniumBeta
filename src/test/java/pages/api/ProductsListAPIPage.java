package pages.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import pages.GeneralPage;
import utils.objects.Products;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class ProductsListAPIPage extends GeneralPage {

    private static final String ENDPOINT = "api/productsList";
    private static JsonPath jsonPath;

    public void configApiHelper() {
        apiHelper.prepareHeaderForAPI(false);
    }

    public void getApiProductList() {
        apiHelper.GET(ENDPOINT);
        jsonPath = apiHelper.getJsonPath();
    }

    public void deleteApiProductList() {
        apiHelper.DELETE(ENDPOINT);
        jsonPath = apiHelper.getJsonPath();
    }

    public void validateStatusCode(int statusCode) {
        apiHelper.validateStatusCode(statusCode);
    }

    public void validateResponseCode(int responseCode) {
        dsl.compare(responseCode, jsonPath.getInt("responseCode"));
    }

    public void validateResponseHeader() {
        Headers headers = apiHelper.getResponse().getHeaders();
        dsl.compare("Content-Type=text/html; charset=utf-8", headers.get("Content-Type").toString());
        dsl.compare("status=200 OK", headers.get("status").toString());
    }

    public void validateProductsListResponse() {

        List<Map<String, Object>> products = jsonPath.getList("products");
        assertNotNull(products);
        Assert.assertFalse(products.isEmpty());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = apiHelper.getResponse().getBody().asString();
            JsonNode root = mapper.readTree(json);
            JsonNode productsNode = root.get("products");
            List<Products> productsList = mapper.readerForListOf(Products.class).readValue(productsNode);
            dsl.compare(34, productsList.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
