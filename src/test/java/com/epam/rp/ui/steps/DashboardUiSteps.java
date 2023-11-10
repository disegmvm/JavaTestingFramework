package com.epam.rp.ui.steps;

import com.epam.rp.ui.pages.DashboardPage;
import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.ui.tests.UiBaseTest;
import com.epam.rp.utils.ConfigReader;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Properties;

public class DashboardUiSteps extends UiBaseTest {

    private DashboardPage dashboardPage;

    @Given("user is logged into Report Portal")
    public void logIntoRp() {
        Properties props = ConfigReader.readProperties();
        String defaultUserName = props.getProperty("testUserName");
        String defaultUserPassword = props.getProperty("testUserPassword");
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get(baseUrl);
        dashboardPage = new DashboardPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(defaultUserName, defaultUserPassword);
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @Given("user selects the first available Dashboard")
    public void selectFirstDashboard() {
        dashboardPage.selectFirstDashboard();
    }

    @Given("user navigates to Dashboards page")
    public void navigateToDashboards() {
        String dashboardsPageUrl = baseUrl + "/ui/#" + projectName + "/dashboard";
        if (!driver.getCurrentUrl().equalsIgnoreCase(dashboardsPageUrl)) {
            driver.get(dashboardsPageUrl);
        }
    }

    @When("user clicks at \"Edit\" button")
    public void clickAtEditDashboard() {
        dashboardPage.clickEditDashboardBtn();
    }

    @When("new name $dashboardName is entered")
    public void enterDashboardName(String dashboardName) {
        dashboardPage.updateDashboardName(dashboardName);
    }

    @When("new description $dashboardDescription is entered")
    public void enterDashboardDescription(String dashboardDescription) {
        dashboardPage.updateDashboardDesc(dashboardDescription);
    }

    @When("user clicks at \"Update\" button")
    public void clickAtUpdateBtn() {
        dashboardPage.clickUpdateDashboardBtn();
    }

    @Then("validate the Dashboard name is $dashboardName")
    public void validateUpdatedDashboard(String dashboardName) {
        Assert.assertEquals(dashboardPage.getDashboardTitle().toLowerCase(), dashboardName.toLowerCase());

    }

}
