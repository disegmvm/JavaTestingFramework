package com.epam.rp.api.tests;

import com.epam.rp.utils.Logger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ApiUserTest extends ApiBaseTest {
//    protected static String apiToken;
//    protected static String baseUrl;

    @BeforeEach
    @BeforeTest
    public void setUp() {
    }

    @Test(description = "API: Get default user details")
    @org.junit.jupiter.api.Test
    public void testGetUserDetails() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .get(baseUrl + "/api/v1/user");

        String responseBody = response.getBody().asString();
        Logger.logDebugMessage("User details: " + responseBody);

        assertNotNull(responseBody, "Response body should not be null");
    }
}
