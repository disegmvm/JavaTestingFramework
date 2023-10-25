package com.epam.rp.api.tests;

import com.epam.rp.utils.ConfigReader;
import com.epam.rp.utils.Logger;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Feature("API Tests")
public class ApiSampleTest {

    private String accessToken;
    private Properties props;

    @BeforeEach
    public void setUp() {
        props = ConfigReader.readProperties();
        String baseUrl = props.getProperty("baseUrl");
        String adminUserName = props.getProperty("adminUserName");
        String adminUserPassword = props.getProperty("adminUserPassword");

        // Get access token
        Map<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", "password");
        formParams.put("username", adminUserName);
        formParams.put("password", adminUserPassword);

        Response response = RestAssured.given()
                .auth().preemptive().basic("ui", "uiman")
                .formParams(formParams)
                .post(baseUrl + "/uat/sso/oauth/token");

        accessToken = response.jsonPath().getString("access_token");
        assertNotNull(accessToken, "Access token should not be null");
    }

    @Test
    public void testGetUserDetails() {
        String baseUrl = props.getProperty("baseUrl");

        // Use the access token to make an authenticated request
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .get(baseUrl + "/api/v1/user");

        String responseBody = response.getBody().asString();
        Logger.logDebugMessage("User details: " + responseBody);

        assertNotNull(responseBody, "Response body should not be null");
    }
}
