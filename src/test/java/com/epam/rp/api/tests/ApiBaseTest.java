package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.io.IOException;

public class ApiBaseTest extends BaseTest {

    protected static String apiToken;
    protected static String dashboardId;

    @BeforeScenario
    @BeforeSuite
    @BeforeEach
    public void setUpSuite() {
        commonSetUp();
        apiToken = props.getProperty("apiToken");
    }

    @AfterScenario
    @AfterSuite
    @AfterEach
    public void tearDownSuite() {
        RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .delete(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);

        commonTearDown();
    }

    public HttpResponse post(String url, String jsonBody) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost(url);
        setHeaders(postRequest);
        postRequest.setEntity(new StringEntity(jsonBody));
        return client.execute(postRequest);
    }

    public HttpResponse get(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(url);
        setHeaders(getRequest);
        return client.execute(getRequest);
    }

    public HttpResponse put(String url, String jsonBody) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpPut putRequest = new HttpPut(url);
        setHeaders(putRequest);
        putRequest.setEntity(new StringEntity(jsonBody));
        return client.execute(putRequest);
    }

    public HttpResponse delete(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpDelete deleteRequest = new HttpDelete(url);
        setHeaders(deleteRequest);
        return client.execute(deleteRequest);
    }

    public void setHeaders(HttpRequestBase request) {
        request.setHeader("Authorization", "Bearer " + apiToken);
        request.setHeader("Content-type", "application/json");
    }

    public JsonNode extractJsonNodeFromResponse(HttpResponse response) throws IOException {
        String responseBody = EntityUtils.toString(response.getEntity());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody);
    }
}
