package com.epam.rp.api.tests.steps;

import com.epam.rp.api.tests.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;

public class DashboardApiSteps extends ApiBaseTest {

    private RequestSpecification createDashboardRequest;
    private Response createDashboardResponse;
    private Response updateDashboardResponse;

    @Given("a POST request to create a Dashboard")
    public void getCreateDashboardRequest(String dashboardName, String dashboardDescription) {
        createDashboardRequest = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", dashboardName, dashboardDescription));
    }

    @When("I send POST request to create a Dashboard")
    public void sendCreateDashboardRequest() {
        createDashboardResponse = createDashboardRequest.post(baseUrl + "/api/v1/" + projectName + "/dashboard");
        dashboardId = createDashboardResponse.jsonPath().getString("id");
    }

    @Then("the Dashboard is created")
    public void validateCreatedDashboard() {
        Assert.assertEquals(createDashboardResponse.statusCode(), 201);
    }

    @Given("an existing Dashboard")
    public void getNewDashboard() {
        Response createResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", "TempDashboardName_33319zzvb", "TempDashboardDescription_33319zzvb"))
                .post(baseUrl + "/api/v1/" + projectName + "/dashboard");
        dashboardId = createResponse.jsonPath().getString("id");
        Assert.assertEquals(createResponse.statusCode(), 201);
    }

    @When("I send PUT request to update an existing Dashboard")
    public void sendUpdateDashboardRequest(String dashboardName, String dashboardDescription) {
        Response updateDashboardResponse = RestAssured.given()
                .header("Authorization", "Bearer " + apiToken)
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "  \"description\": \"Updated %s\",\n" +
                        "  \"name\": \"Updated %s\"\n" +
                        "}", dashboardDescription, dashboardName))
                .put(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);
    }

    @Then("the Dashboard is updated")
    public void validateUpdatedDashboard() {
        Assertions.assertEquals(200, updateDashboardResponse.statusCode());
    }

}
