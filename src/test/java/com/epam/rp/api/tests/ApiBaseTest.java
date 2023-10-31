package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

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
}
