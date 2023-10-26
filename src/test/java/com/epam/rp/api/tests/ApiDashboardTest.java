package com.epam.rp.api.tests;

import com.epam.rp.utils.Logger;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Feature("Dashboard")
public class ApiDashboardTest extends ApiBaseTest{

    @BeforeEach
    @BeforeTest
    public void setUp() {
    }

    @Test(description = "API: Create Dashboard")
    @org.junit.jupiter.api.Test
    public void testCreateDashboard() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"description\": \"Test Dashboard desc\",\n" +
                        "  \"name\": \"Test Dashboard 222\"\n" +
                        "}")
                .post(baseUrl + "/api/v1/" + projectName + "/dashboard");

        String responseBody = response.getBody().asString();
        Logger.logDebugMessage("Created Dashboard details: " + responseBody);

        assertEquals(response.statusCode(), 201);
        assertNotNull(responseBody, "Response body should not be null");
    }

    @Test(description = "API: Update Dashboard")
    @org.junit.jupiter.api.Test
    public void testUpdateDashboard() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"name\": \"Brand New Dashboard name\"\n" +
                        "}")
                .put(baseUrl + "/api/v1/" + projectName + "/dashboard/131126");

        String responseBody = response.getBody().asString();
        Logger.logDebugMessage("Created Dashboard details: " + responseBody);

        assertEquals(response.statusCode(), 200);
        assertNotNull(responseBody, "Response body should not be null");
    }
}
