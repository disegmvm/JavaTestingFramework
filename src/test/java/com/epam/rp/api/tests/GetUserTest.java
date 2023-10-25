package com.epam.rp.api.tests;

import com.epam.rp.utils.Logger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

@Feature("Get user API test")
public class GetUserTest extends ApiBaseTest {
    private String accessToken;

    @BeforeTest
    public void setUp() {
        String baseUrl = props.getProperty("baseUrl");
        String adminUserName = props.getProperty("adminUserName");
        String adminUserPassword = props.getProperty("adminUserPassword");

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

    @Story("Get user API test story")
    @Test
    public void testGetUserDetails() {
        String baseUrl = props.getProperty("baseUrl");

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .get(baseUrl + "/api/v1/user");

        String responseBody = response.getBody().asString();
        Logger.logDebugMessage("User details: " + responseBody);

        assertNotNull(responseBody, "Response body should not be null");
    }
}
