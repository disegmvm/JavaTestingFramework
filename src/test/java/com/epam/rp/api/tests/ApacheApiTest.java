package com.epam.rp.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

public class ApacheApiTest extends ApiBaseTest {
    @Test
    public void testCreateDashboard_ValidParameters() throws IOException {
        String randomDescription = "Desc " + UUID.randomUUID();
        String randomName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, randomName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void testCreateDashboard_EmptyDashboardName() throws IOException {
        String jsonBody = "{ \"description\": \"Test description\", \"name\": \"\" }";
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 400);

        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String errMessage = rootNode.path("message").asText();
        Assert.assertTrue(errMessage.contains("Incorrect Request"));
    }

    @Test
    public void testCreateDashboard_ExistingDashboardName() throws IOException {
        // Step #1: Creating new Dashboard
        String randomDescription = "Desc " + UUID.randomUUID();
        String dashboardName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, dashboardName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201, "Couldn't create the initial dashboard");

        // Step #2: Creating new Dashboard with the same name
        response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 409);

        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String errMessage = rootNode.path("message").asText();
        Assert.assertTrue(errMessage.contains("You couldn't create the duplicate"));
    }

    @Test
    public void testGetDashboards_ValidParameters() throws IOException {
        HttpResponse response = get(baseUrl + "/api/v1/" + projectName + "/dashboard");
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String responseBody = EntityUtils.toString(response.getEntity());
        Assert.assertFalse(responseBody.isEmpty());
    }

    @Test
    public void testGetDashboards_InvalidProjectName() throws IOException {
        HttpResponse response = get(baseUrl + "/api/v1/prjct/dashboard");
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 403);
    }

    @Test
    public void testUpdateDashboard_ValidParameters() throws IOException {
        // Step #1: Create new Dashboard
        String randomDescription = "Desc " + UUID.randomUUID();
        String dashboardName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, dashboardName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String dashboardId = rootNode.path("id").asText();
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201, "Couldn't create the initial dashboard");

        // Step #2: Update the dashboard
        jsonBody = String.format("{\"description\": \"New description\", \"name\": \"%s\"}", dashboardName);
        String url = baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId;
        response = put(url, jsonBody);
        statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200, "Couldn't update the dashboard");

        // Step #3: Validate the updated dashboard name
        response = get(url);
        statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200);

        rootNode = extractJsonNodeFromResponse(response);
        String actualDashboardName = rootNode.path("name").asText();
        Assert.assertEquals(actualDashboardName, dashboardName);
    }

    @Test
    public void testUpdateDashboard_EmptyDashboardName() throws IOException {
        String randomDescription = "Desc " + UUID.randomUUID();
        String dashboardName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, dashboardName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String dashboardId = rootNode.path("id").asText();
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);

        jsonBody = "{\"description\": \"New description\", \"name\": \"\"}";
        response = put(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId, jsonBody);
        statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 400);

        rootNode = extractJsonNodeFromResponse(response);
        String errMessage = rootNode.path("message").asText();
        Assert.assertTrue(errMessage.contains("Incorrect Request"));
    }

    @Test
    public void testUpdateDashboard_EmptyPayload() throws IOException {
        // Step #1: Create new Dashboard
        String randomDescription = "Desc " + UUID.randomUUID();
        String dashboardName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, dashboardName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String dashboardId = rootNode.path("id").asText();
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);

        // Step #2: Update the dashboard
        jsonBody = "{}";
        response = put(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId, jsonBody);
        statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 400);

        rootNode = extractJsonNodeFromResponse(response);
        String errMessage = rootNode.path("message").asText();
        Assert.assertTrue(errMessage.contains("Incorrect Request"));
    }

    @Test
    public void testDeleteDashboard_ValidParameters() throws IOException {
        // Step #1: Create new Dashboard
        String randomDescription = "Desc " + UUID.randomUUID();
        String dashboardName = "Name " + UUID.randomUUID();
        String jsonBody = String.format("{ \"description\": \"%s\", \"name\": \"%s\" }", randomDescription, dashboardName);
        HttpResponse response = post(baseUrl + "/api/v1/" + projectName + "/dashboard", jsonBody);
        JsonNode rootNode = extractJsonNodeFromResponse(response);
        String dashboardId = rootNode.path("id").asText();
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 201);

        // Step #2: Delete the dashboard
        HttpResponse deleteResponse = delete(baseUrl + "/api/v1/" + projectName + "/dashboard/" + dashboardId);
        Assert.assertEquals(deleteResponse.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void testDeleteDashboard_InvalidDashboardId() throws IOException {
        HttpResponse deleteResponse = delete(baseUrl + "/api/v1/" + projectName + "/dashboard/0");
        Assert.assertEquals(deleteResponse.getStatusLine().getStatusCode(), 404);

        JsonNode rootNode = extractJsonNodeFromResponse(deleteResponse);
        String errMessage = rootNode.path("message").asText();
        Assert.assertTrue(errMessage.contains("not found"));
    }
}
