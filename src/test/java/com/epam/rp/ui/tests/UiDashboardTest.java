package com.epam.rp.ui.tests;

import com.epam.rp.ui.pages.DashboardPage;
import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Properties;

public class UiDashboardTest extends UiBaseTest {

    private DashboardPage dashboardPage;

    @BeforeMethod
    @BeforeEach
    public void setUpTest() {
        Properties props = ConfigReader.readProperties();
        String defaultUserName = props.getProperty("testUserName");
        String defaultUserPassword = props.getProperty("testUserPassword");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String dashboardsPageUrl = baseUrl + "/ui/#" + projectName + "/dashboard";

        driver.get(baseUrl);
        dashboardPage = new DashboardPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(defaultUserName, defaultUserPassword);
        wait.until(ExpectedConditions.urlContains("dashboard"));

        if (!driver.getCurrentUrl().equalsIgnoreCase(dashboardsPageUrl)) {
            driver.get(dashboardsPageUrl);
        }
    }

    @DataProvider(name = "dashboardNames")
    public Object[][] provideData() {
        return new Object[][]{
                {"New Dashboard name 1"},
                {"Another Dashboard new name"},
                {"Brand New Dashboard name"},
                {"Kind of new Dashboard name"},
                {"New Test Dashboard name"}
        };
    }

    @Test(description = "UI: Update Dashboard name", dataProvider = "dashboardNames")
    @ParameterizedTest
    @ValueSource(strings = {"New Dashboard name 1", "Another Dashboard new name", "Brand New Dashboard name", "Kind of new Dashboard name", "New Test Dashboard name"})
    public void testUpdateDashboardName(String newName) {
        dashboardPage.selectFirstDashboard();
        dashboardPage.updateDashboardName(newName);
        Assertions.assertTrue(dashboardPage.getDashboardTitle().equalsIgnoreCase(newName));

        Allure.addAttachment("Login screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
