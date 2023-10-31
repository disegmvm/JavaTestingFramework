package com.epam.rp.api.tests;

import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

@Feature("Dashboard")
public class ApiDashboardTest extends ApiBaseTest {

    @BeforeEach
    @BeforeTest
    public void setUp() {
    }

    @DataProvider(name = "dashboardData")
    public Object[][] testngDashboardDataProvider() {
        return new Object[][]{
                {"Test Dashboard #100", "Test Dashboard desc #1"},
                {"Test Dashboard #200", "Test Dashboard desc #2"},
                {"Test Dashboard #300", "Test Dashboard desc #3"},
                {"Test Dashboard #400", "Test Dashboard desc #4"},
                {"Test Dashboard #500", "Test Dashboard desc #5"}
        };
    }

    static Stream<String[]> junitDashboardDataProvider() {
        return Stream.of(
                new String[]{"Test Dashboard #100", "Test Dashboard desc #1"},
                new String[]{"Test Dashboard #200", "Test Dashboard desc #2"},
                new String[]{"Test Dashboard #300", "Test Dashboard desc #3"},
                new String[]{"Test Dashboard #400", "Test Dashboard desc #4"},
                new String[]{"Test Dashboard #500", "Test Dashboard desc #5"}
        );
    }

    @Test(description = "API: Create Dashboard", dataProvider = "dashboardData")
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("junitDashboardDataProvider")
    public void testCreateDashboard(String dashboardName, String dashboardDescription) {
        Response createResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", dashboardDescription, dashboardName))
                .post(baseUrl + "/api/v1/" + projectName + "/dashboard");

        Assert.assertEquals(createResponse.statusCode(), 201);

        String dashboardId = createResponse.jsonPath().getString("id");
        Response deleteResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .delete(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);

        Assert.assertEquals(deleteResponse.statusCode(), 200);
    }

    @Test(description = "API: Update Dashboard", dataProvider = "dashboardData")
    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("junitDashboardDataProvider")
    public void testUpdateDashboard(String dashboardName, String dashboardDescription) {
        Response createResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", dashboardDescription, dashboardName))
                .post(baseUrl + "/api/v1/" + projectName + "/dashboard");
        String dashboardId = createResponse.jsonPath().getString("id");
        Assertions.assertEquals(201, createResponse.statusCode());

        Response updateResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"Updated %s\",\n" +
                        "  \"name\": \"Updated %s\"\n" +
                        "}", dashboardDescription, dashboardName))
                .put(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);
        Assertions.assertEquals(200, updateResponse.statusCode());

        Response deleteResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .delete(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);
        Assertions.assertEquals(200, deleteResponse.statusCode());
    }

    @AfterEach
    @AfterTest
    public void tearDown() {
    }

}

